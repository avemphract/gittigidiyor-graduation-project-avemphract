package dev.patika.creditscoreserver.api.controllers;

import dev.patika.creditscoreserver.business.dto.CreditScoreDto;
import dev.patika.creditscoreserver.business.services.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditScores")
public class CreditScoreController implements BaseController<CreditScoreDto,Long> {
    private final CreditScoreService creditScoreService;


    @Autowired
    public CreditScoreController(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CreditScoreDto>> findAll() {
        return ResponseEntity.ok(creditScoreService.toDto(creditScoreService.findAll()));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CreditScoreDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(creditScoreService.toDto(creditScoreService.findById(id)));
    }

    @GetMapping("/tc/{tcNumber}")
    public ResponseEntity<CreditScoreDto> findByTcNumber(@PathVariable Long tcNumber){
        return ResponseEntity.ok(creditScoreService.toDto(creditScoreService.findByTcNumber(tcNumber)));
    }

    @Override
    @PostMapping
    public ResponseEntity<CreditScoreDto> save(@RequestParam CreditScoreDto body) {
        return ResponseEntity.ok(creditScoreService.toDto(creditScoreService.save(creditScoreService.toEntity(body))));
    }

    @Override
    @PutMapping
    public ResponseEntity<CreditScoreDto> update(@RequestParam CreditScoreDto body) {
        return ResponseEntity.ok(creditScoreService.toDto(creditScoreService.update(creditScoreService.toEntity(body))));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<CreditScoreDto> delete(@RequestParam CreditScoreDto body) {
        return ResponseEntity.ok(creditScoreService.toDto(creditScoreService.delete(creditScoreService.toEntity(body))));
    }
}
