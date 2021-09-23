package dev.patika.customerserver.api.controllers;

import dev.patika.customerserver.business.dto.MessageDto;
import dev.patika.customerserver.business.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController implements BaseController<MessageDto,Long> {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MessageDto>> findAll() {
        return ResponseEntity.ok(messageService.toDto(messageService.findAll()));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.toDto(messageService.findById(id)));
    }

    @Override
    @PostMapping
    public ResponseEntity<MessageDto> save(@RequestBody MessageDto body) {
        return ResponseEntity.ok(messageService.toDto(messageService.save(messageService.toEntity(body))));
    }

    @Override
    @PutMapping
    public ResponseEntity<MessageDto> update(@RequestBody MessageDto body) {
        return ResponseEntity.ok(messageService.toDto(messageService.update(messageService.toEntity(body))));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<MessageDto> delete(@RequestBody MessageDto body) {
        return ResponseEntity.ok(messageService.toDto(messageService.delete(messageService.toEntity(body))));
    }
}
