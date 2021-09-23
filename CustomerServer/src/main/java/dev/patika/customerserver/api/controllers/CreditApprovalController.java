package dev.patika.customerserver.api.controllers;

import dev.patika.customerserver.business.dto.CreditApprovalDto;
import dev.patika.customerserver.business.service.CreditApprovalService;
import dev.patika.customerserver.repositories.CreditApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditApprovals")
public class CreditApprovalController implements BaseController<CreditApprovalDto,Long> {
    private final CreditApprovalService creditApprovalService;

    @Autowired
    public CreditApprovalController(CreditApprovalService creditApprovalService) {
        this.creditApprovalService=creditApprovalService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CreditApprovalDto>> findAll() {
        return ResponseEntity.ok(creditApprovalService.toDto(creditApprovalService.findAll()));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CreditApprovalDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(creditApprovalService.toDto(creditApprovalService.findById(id)));
    }

    @Override
    @PostMapping
    public ResponseEntity<CreditApprovalDto> save(@RequestBody CreditApprovalDto body) {
        return ResponseEntity.ok(creditApprovalService.toDto(creditApprovalService.save(creditApprovalService.toEntity(body))));
    }

    @Override
    @PutMapping
    public ResponseEntity<CreditApprovalDto> update(@RequestBody CreditApprovalDto body) {
        return ResponseEntity.ok(creditApprovalService.toDto(creditApprovalService.update(creditApprovalService.toEntity(body))));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<CreditApprovalDto> delete(@RequestBody CreditApprovalDto body) {
        return ResponseEntity.ok(creditApprovalService.toDto(creditApprovalService.delete(creditApprovalService.toEntity(body))));
    }
}
