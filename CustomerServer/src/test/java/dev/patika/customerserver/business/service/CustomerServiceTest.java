package dev.patika.customerserver.business.service;

import dev.patika.customerserver.api.controllers.CreditScoreClient;
import dev.patika.customerserver.api.exceptions.*;
import dev.patika.customerserver.business.dto.CreditApprovalDto;
import dev.patika.customerserver.business.dto.CreditScoreDto;
import dev.patika.customerserver.business.mapper.CreditApprovalMapper;
import dev.patika.customerserver.business.mapper.CreditApprovalMapperImpl;
import dev.patika.customerserver.business.mapper.CustomerMapper;
import dev.patika.customerserver.entities.CreditApproval;
import dev.patika.customerserver.entities.Customer;
import dev.patika.customerserver.repositories.CustomerRepository;
import dev.patika.customerserver.utils.CustomerCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


//@SpringBootTest
@ExtendWith({MockitoExtension.class})
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CreditScoreClient creditScoreClient;
    @Mock
    CreditApprovalService creditApprovalService;
    @Mock
    CreditApprovalMapper creditApprovalMapper;

    @InjectMocks
    private CustomerService customerService;


    private static Stream<Arguments> parameters_save_throw_not_valid_name() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("     "),
                Arguments.of("---"),
                Arguments.of("5"),
                Arguments.of("Emre."),
                Arguments.of("Nurn5"),
                Arguments.of("?^ ")
        );
    }

    private static Stream<Arguments> parameters_save_not_throw_not_valid_name() {
        return Stream.of(
                Arguments.of("Emre"),
                Arguments.of("ÇĞŞİÖL"),
                Arguments.of("TEST SENARYO"),
                Arguments.of("oıutyu")
        );
    }

    private static Stream<Arguments> parameters_save_throw_not_valid_tcNumber() {
        return Stream.of(
                Arguments.of(14L),
                Arguments.of(0L),
                Arguments.of(1234567890L),
                Arguments.of(12345678901L),
                Arguments.of(10000000001L),
                Arguments.of(100000000012L),
                Arguments.of(-1),
                Arguments.of(-10000),
                Arguments.of(-12345678901L),
                Arguments.of(-12345678902L)
        );
    }

    private static Stream<Arguments> parameters_save_throw_not_valid_phoneNumber() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(1),
                Arguments.of(1000000000),
                Arguments.of(6000000000L),
                Arguments.of(50000000000L),
                Arguments.of(-1),
                Arguments.of(-1000000000),
                Arguments.of(-6000000000L),
                Arguments.of(-5000000000L),
                Arguments.of(-5100000000L),
                Arguments.of(-50000000000L)
        );
    }

    @BeforeEach
    void setUp() {
        lenient().when(creditApprovalMapper.toDto(any(CreditApproval.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                CreditApproval creditApproval= (CreditApproval) invocationOnMock.getArguments()[0];
                return new CreditApprovalDto(creditApproval.getId(),creditApproval.isApproval(),creditApproval.getGivenCreditAmount(),creditApproval.getCustomer().getTcNumber());
            }
        });
        lenient().when(creditApprovalService.save(any())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return invocationOnMock.getArguments()[0];
            }
        });
        lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(new Answer<Customer>() {
            @Override
            public Customer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return (Customer) invocationOnMock.getArguments()[0];
            }
        });
        lenient().when(creditApprovalService.save(any(CreditApproval.class))).thenAnswer(new Answer<CreditApproval>() {
            @Override
            public CreditApproval answer(InvocationOnMock invocationOnMock) throws Throwable {
                return (CreditApproval) invocationOnMock.getArguments()[0];
            }
        });

    }

    @Test
    void findAll() {
        List<Customer> expected = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(expected);

        List<Customer> actual = customerService.findAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        Customer expected = new Customer();
        when(customerRepository.findById(any())).thenReturn(Optional.of(expected));

        Customer actual = customerService.findById(any());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findByTcNumber() {
        Customer expected = new Customer();
        when(customerRepository.findByTckn(anyLong())).thenReturn(expected);

        Customer actual = customerService.findByTcNumber(anyInt());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save() {
        Customer expected = CustomerCreator.createValidCustomer();
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(false);

        Customer actual = customerService.save(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save_throw_entity_already_exist() {
        Customer expected = CustomerCreator.createValidCustomer();
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(true);

        assertThrows(EntityAlreadyExistsException.class, () -> customerService.save(expected));
    }

    @ParameterizedTest
    @MethodSource("parameters_save_throw_not_valid_name")
    void save_throw_not_valid_name(String name) {
        Customer expected = new Customer(CustomerCreator.getValidTcNumber(), name, CustomerCreator.getValidNameOrSurname(), CustomerCreator.getSalary(), CustomerCreator.getValidPhoneNumber());
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(false);

        assertThrows(NotValidCustomerNameException.class, () -> customerService.save(expected));

    }

    @ParameterizedTest
    @MethodSource("parameters_save_not_throw_not_valid_name")
    void save_not_throw_not_valid_name(String name) {
        Customer expected = new Customer(CustomerCreator.getValidTcNumber(), name, CustomerCreator.getValidNameOrSurname(), CustomerCreator.getSalary(), CustomerCreator.getValidPhoneNumber());
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(false);

        customerService.save(expected);

    }

    @ParameterizedTest
    @MethodSource("parameters_save_throw_not_valid_tcNumber")
    void save_throw_not_valid_tcNumber(long tcNumber) {
        Customer expected = new Customer(tcNumber, CustomerCreator.getValidNameOrSurname(), CustomerCreator.getValidNameOrSurname(), CustomerCreator.getSalary(), CustomerCreator.getValidPhoneNumber());
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(false);

        assertThrows(NotValidTcNumberException.class, () -> customerService.save(expected));
    }

    @ParameterizedTest
    @MethodSource("parameters_save_throw_not_valid_phoneNumber")
    void save_throw_not_valid_phoneNumber(long phoneNumber) {
        Customer expected = new Customer(CustomerCreator.getValidTcNumber(), CustomerCreator.getValidNameOrSurname(), CustomerCreator.getValidNameOrSurname(), CustomerCreator.getSalary(), phoneNumber);
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(false);

        assertThrows(NotValidPhoneNumberException.class, () -> customerService.save(expected));
    }

    @Test
    void update() {
        Customer expected = CustomerCreator.createValidCustomer();
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(true);
        when(customerRepository.findByTckn(expected.getTcNumber())).thenReturn(expected);

        Customer actual = customerService.update(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void update_throw_entity_not_found() {
        Customer expected = CustomerCreator.createValidCustomer();
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> customerService.update(expected));
    }

    @Test
    void delete() {
        Customer expected = CustomerCreator.createValidCustomer();
        when(customerRepository.findByTckn(expected.getTcNumber())).thenReturn(expected);

        Customer actual = customerService.delete(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void creditApplication() {
        Customer customer = CustomerCreator.createValidCustomer();

        when(customerRepository.findByTckn(anyLong())).thenReturn(customer);
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(true);

        when(creditScoreClient.findByTcNumber(anyLong())).thenReturn(ResponseEntity.ok(new CreditScoreDto(customer.getTcNumber(),0)));

        CreditApprovalDto actual = customerService.creditApplication(customer);

        assertAll(
                () -> assertNotNull(actual)
        );
    }


    @ParameterizedTest
    @ValueSource(doubles = {0,1,2,10,100,400,499})
    void creditApplication_first_condition(double creditScore) {
        Customer customer = CustomerCreator.createValidCustomer();
        CreditScoreDto creditScoreDto = new CreditScoreDto(customer.getTcNumber(), creditScore);

        when(customerRepository.findByTckn(anyLong())).thenReturn(customer);
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(true);

        when(creditScoreClient.findByTcNumber(anyLong())).thenReturn(ResponseEntity.ok(creditScoreDto));

        CreditApprovalDto actual = customerService.creditApplication(customer);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getCustomerTcNumber(), customer.getTcNumber()),
                () -> assertFalse(actual.isApproval()),
                () -> assertEquals(actual.getGivenCreditAmount(), 0)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {500,501,600,700,900,999,1000})
    void creditApplication_second_condition(double creditScore) {
        Customer customer = new Customer(CustomerCreator.getValidTcNumber(),CustomerCreator.getValidNameOrSurname(),CustomerCreator.getValidNameOrSurname(),5000,CustomerCreator.getValidPhoneNumber());
        CreditScoreDto creditScoreDto = new CreditScoreDto(customer.getTcNumber(), creditScore);

        when(customerRepository.findByTckn(anyLong())).thenReturn(customer);
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(true);

        when(creditScoreClient.findByTcNumber(anyLong())).thenReturn(ResponseEntity.ok(creditScoreDto));

        CreditApprovalDto actual = customerService.creditApplication(customer);

        assertNotNull(actual);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getCustomerTcNumber(), customer.getTcNumber()),
                () -> assertTrue(actual.isApproval()),
                () -> assertEquals(actual.getGivenCreditAmount(), 10000)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {500,501,600,700,900,999,1000})
    void creditApplication_third_condition(double creditScore) {
        Customer customer = new Customer(CustomerCreator.getValidTcNumber(),CustomerCreator.getValidNameOrSurname(),CustomerCreator.getValidNameOrSurname(),5001,CustomerCreator.getValidPhoneNumber());
        CreditScoreDto creditScoreDto = new CreditScoreDto(customer.getTcNumber(), creditScore);

        when(customerRepository.findByTckn(anyLong())).thenReturn(customer);
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(true);

        when(creditScoreClient.findByTcNumber(anyLong())).thenReturn(ResponseEntity.ok(creditScoreDto));

        CreditApprovalDto actual = customerService.creditApplication(customer);

        assertNotNull(actual);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getCustomerTcNumber(), customer.getTcNumber()),
                () -> assertTrue(actual.isApproval()),
                () -> assertEquals(actual.getGivenCreditAmount(), 20000)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {1001,1002,2000,4000,10000})
    void creditApplication_fourth_condition(double creditScore) {
        Customer customer = new Customer(CustomerCreator.getValidTcNumber(),CustomerCreator.getValidNameOrSurname(),CustomerCreator.getValidNameOrSurname(),5001,CustomerCreator.getValidPhoneNumber());
        CreditScoreDto creditScoreDto = new CreditScoreDto(customer.getTcNumber(), creditScore);

        when(customerRepository.findByTckn(anyLong())).thenReturn(customer);
        when(customerRepository.isExistByTckn(anyLong())).thenReturn(true);

        when(creditScoreClient.findByTcNumber(anyLong())).thenReturn(ResponseEntity.ok(creditScoreDto));

        CreditApprovalDto actual = customerService.creditApplication(customer);

        assertNotNull(actual);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getCustomerTcNumber(), customer.getTcNumber()),
                () -> assertTrue(actual.isApproval()),
                () -> assertEquals(actual.getGivenCreditAmount(), customer.getSalary()*4)
        );
    }
}