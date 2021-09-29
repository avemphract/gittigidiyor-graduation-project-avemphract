package dev.patika.customerserver.api.controllers;

import dev.patika.customerserver.business.dto.CreditScoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "credit-score-service")
public interface CreditScoreClient {

    @GetMapping("/api/creditScores/tc/{tcNumber}")
    ResponseEntity<CreditScoreDto> findByTcNumber(@PathVariable Long tcNumber);

}
