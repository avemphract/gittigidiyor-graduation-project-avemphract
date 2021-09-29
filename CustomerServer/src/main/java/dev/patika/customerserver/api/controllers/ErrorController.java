package dev.patika.customerserver.api.controllers;

import dev.patika.customerserver.business.dto.ErrorDto;
import dev.patika.customerserver.business.service.ErrorService;
import dev.patika.customerserver.entities.ErrorEntity;
import dev.patika.customerserver.repositories.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/errors")
public class ErrorController implements BaseController<ErrorDto, String>{
    private final ErrorService errorService;

    @Autowired
    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<ErrorDto>> findAll() {
        return ResponseEntity.ok(errorService.toDto(errorService.findAll()));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ErrorDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(errorService.toDto(errorService.findById(id)));
    }
}
