package dev.patika.customerserver.api.controllers;

import dev.patika.customerserver.business.dto.CreditApprovalDto;
import dev.patika.customerserver.business.dto.CustomerDto;
import dev.patika.customerserver.business.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController implements BaseController<CustomerDto,Long> {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(customerService.toDto(customerService.findAll()));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.toDto(customerService.findById(id)));
    }

    @GetMapping("/tc/{tc}")
    public ResponseEntity<CustomerDto> findByCustomer(@PathVariable Long tc){
        return ResponseEntity.ok(customerService.toDto(customerService.findByTcNumber(tc)));
    }

    @PostMapping("/creditApplication")
    public ResponseEntity<CreditApprovalDto> creditApplication(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(customerService.creditApplication(customerService.toEntity(customerDto)));
    }

    @Override
    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto body) {
        return ResponseEntity.ok(customerService.toDto(customerService.save(customerService.toEntity(body))));
    }

    @Override
    @PutMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto body) {
        return ResponseEntity.ok(customerService.toDto(customerService.update(customerService.toEntity(body))));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<CustomerDto> delete(@RequestBody CustomerDto body) {
        return ResponseEntity.ok(customerService.toDto(customerService.delete(customerService.toEntity(body))));
    }
}
