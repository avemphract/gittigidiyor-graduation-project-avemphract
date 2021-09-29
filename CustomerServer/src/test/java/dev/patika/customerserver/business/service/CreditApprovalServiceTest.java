package dev.patika.customerserver.business.service;

import dev.patika.customerserver.api.exceptions.EntityAlreadyExistsException;
import dev.patika.customerserver.api.exceptions.EntityNotFoundException;
import dev.patika.customerserver.entities.CreditApproval;
import dev.patika.customerserver.entities.Customer;
import dev.patika.customerserver.entities.Message;
import dev.patika.customerserver.repositories.CreditApprovalRepository;
import dev.patika.customerserver.utils.CustomerCreator;
import dev.patika.customerserver.utils.MessageFactory;
import dev.patika.customerserver.utils.RequestInfo;
import dev.patika.customerserver.validators.CustomerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class CreditApprovalServiceTest {

    @Mock
    CreditApprovalRepository creditApprovalRepository;
    @Mock
    MessageService messageService;
    @Mock
    MessageFactory messageFactory;
    @Mock
    RequestInfo requestInfo;

    @InjectMocks
    CreditApprovalService creditApprovalService;

    @BeforeEach
    void setUp() {
        messageFactory=new MessageFactory();
        requestInfo=new RequestInfo();
    }

    @Test
    void findAll() {
        List<CreditApproval> expected = new ArrayList<>();
        when(creditApprovalRepository.findAll()).thenReturn(expected);

        List<CreditApproval> actual = creditApprovalService.findAll();

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected.size(),actual.size()),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void findById() {
        CreditApproval expected=new CreditApproval();
        when(creditApprovalRepository.findById(any())).thenReturn(Optional.of(expected));

        CreditApproval actual=creditApprovalService.findById(any());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }


    @Test
    void findByCustomer() {
        Customer customer=CustomerCreator.createValidCustomer();
        List<CreditApproval> expected= Arrays.asList(
                new CreditApproval(true, 1000,customer),
                new CreditApproval(true,2000,customer),
                new CreditApproval(false,0,customer));
        when(creditApprovalRepository.findByCustomerTc(customer.getTcNumber())).thenReturn(expected);

        List<CreditApproval> actual = creditApprovalService.findByCustomer(customer.getTcNumber());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected.size(),actual.size()),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void save() {
        CreditApproval expected=new CreditApproval(true,0,new Customer(),null,null,null);
        expected.setCustomer(new Customer());
        when(messageService.save(any())).thenReturn(new Message());
        when(creditApprovalRepository.save(any())).thenReturn(expected);

        CreditApproval actual = creditApprovalService.save(expected);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void save_throw_entity_already_exception(){
        when(creditApprovalRepository.isExistById(anyLong())).thenReturn(true);

        assertAll(
                ()->assertThrows(EntityAlreadyExistsException.class,()->creditApprovalService.save(new CreditApproval()))
        );
    }

    @Test
    void update() {
        CreditApproval expected=new CreditApproval(true,0,new Customer(),null,null,null);
        expected.setCustomer(new Customer());
        when(messageService.save(any())).thenReturn(new Message());
        when(creditApprovalRepository.isExistById(anyLong())).thenReturn(false);
        when(creditApprovalRepository.save(any())).thenReturn(expected);

        CreditApproval actual = creditApprovalService.save(expected);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void update_throw_entity_not_found_exception(){
        when(creditApprovalRepository.isExistById(anyLong())).thenReturn(false);

        assertAll(
                ()->assertThrows(EntityNotFoundException.class,()->creditApprovalService.update(new CreditApproval()))
        );
    }

    @Test
    void delete() {
        when(creditApprovalRepository.isExistById(anyLong())).thenReturn(true);
        CreditApproval expected=new CreditApproval();

        CreditApproval actual=creditApprovalService.delete(expected);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void delete_throw_entity_not_found(){
        when(creditApprovalRepository.isExistById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class,()->creditApprovalService.delete(new CreditApproval()));
    }

}