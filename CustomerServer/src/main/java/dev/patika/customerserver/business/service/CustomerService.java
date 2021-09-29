package dev.patika.customerserver.business.service;

import dev.patika.customerserver.api.controllers.CreditScoreClient;
import dev.patika.customerserver.api.exceptions.EntityAlreadyExistsException;
import dev.patika.customerserver.api.exceptions.EntityNotFoundException;
import dev.patika.customerserver.business.dto.CreditApprovalDto;
import dev.patika.customerserver.business.dto.CreditScoreDto;
import dev.patika.customerserver.business.dto.CustomerDto;
import dev.patika.customerserver.business.mapper.CreditApprovalMapper;
import dev.patika.customerserver.business.mapper.CustomerMapper;
import dev.patika.customerserver.entities.CreditApproval;
import dev.patika.customerserver.entities.Customer;
import dev.patika.customerserver.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static dev.patika.customerserver.validators.CustomerValidator.*;

@Service
public class CustomerService implements BaseService<CustomerDto,Customer,Long> {

    private final CustomerRepository customerRepository;
    private final CreditScoreClient creditScoreClient;
    private final CreditApprovalService creditApprovalService;
    @Autowired
    private CustomerMapper mapper;

    private final CreditApprovalMapper creditApprovalMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CreditScoreClient creditScoreClient, CreditApprovalService creditApprovalService, CreditApprovalMapper creditApprovalMapper) {
        this.customerRepository = customerRepository;
        this.creditScoreClient = creditScoreClient;
        this.creditApprovalService = creditApprovalService;
        this.creditApprovalMapper=creditApprovalMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll().forEach(customerList::add);
        return customerList;
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Customer findByTcNumber(long tcNumber){
        return customerRepository.findByTckn(tcNumber);
    }

    @Override
    @Transactional
    public Customer save(Customer object) {
        if (customerRepository.isExistByTckn(object.getTcNumber()))
            throw new EntityAlreadyExistsException(object.getTcNumber()+" tc number already registered in customer table");
        object.setName(nameSurnameValidator(object.getName(), Type.NAME));
        object.setSurname(nameSurnameValidator(object.getSurname(),Type.SURNAME));
        tcNumberValidator(object.getTcNumber());
        phoneNumberValidator(object.getPhoneNumber());
        return customerRepository.save(object);
    }

    @Override
    @Transactional
    public Customer update(Customer object) {
        if (!customerRepository.isExistByTckn(object.getTcNumber()))
            throw new EntityNotFoundException(object.getTcNumber()+" tcNumber has not found in customer table");
        object.setId(customerRepository.findByTckn(object.getTcNumber()).getId());
        object.setName(nameSurnameValidator(object.getName(), Type.NAME));
        object.setSurname(nameSurnameValidator(object.getSurname(),Type.SURNAME));
        tcNumberValidator(object.getTcNumber());
        phoneNumberValidator(object.getPhoneNumber());
        return customerRepository.save(object);
    }

    @Override
    @Transactional
    public Customer delete(Customer object) {
        Customer customer = customerRepository.findByTckn(object.getTcNumber());
        if (customer == null)
            throw new EntityNotFoundException(object.getTcNumber()+" tcNumber has not found in customer table");
        customerRepository.delete(customer);
        return object;
    }

    @Transactional
    public CreditApprovalDto creditApplication(Customer customer) {
        Customer databaseCustomer = customerRepository.findByTckn(customer.getTcNumber());
        if (databaseCustomer == null)
            customer=save(customer);
        else
            customer=update(customer);

        CreditApproval creditApproval = new CreditApproval();
        creditApproval.setCustomer(customer);

        CreditScoreDto creditScoreDto = creditScoreClient.findByTcNumber(customer.getTcNumber()).getBody();

        if (1000 < creditScoreDto.getCreditScore()) {
            creditApproval.setApproval(true);
            creditApproval.setGivenCreditAmount(customer.getSalary() * 4);
        } else if (500 <= creditScoreDto.getCreditScore() && 5000 < customer.getSalary()) {
            creditApproval.setApproval(true);
            creditApproval.setGivenCreditAmount(20000);
        }else if (500 <= creditScoreDto.getCreditScore()){
            creditApproval.setApproval(true);
            creditApproval.setGivenCreditAmount(10000);
        }else {
            creditApproval.setApproval(false);
        }
        return creditApprovalMapper.toDto(creditApprovalService.save(creditApproval));
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        return mapper.toDto(customer);
    }

    @Override
    public Customer toEntity(CustomerDto customerDto) {
        return mapper.toEntity(customerDto);
    }

}
