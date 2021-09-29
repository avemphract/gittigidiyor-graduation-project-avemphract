package dev.patika.customerserver.business.service;

import dev.patika.customerserver.entities.Message;
import dev.patika.customerserver.repositories.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    MessageService messageService;

    @Test
    void findAll() {
        List<Message> expected = new ArrayList<>();
        when(messageRepository.findAll()).thenReturn(expected);

        List<Message> actual = messageService.findAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        Message expected = new Message();
        when(messageRepository.findById(any())).thenReturn(Optional.of(expected));

        Message actual = messageService.findById(any());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save() {
        Message expected = new Message();
        when(messageRepository.isExistById(anyLong())).thenReturn(false);
        when(messageService.save(expected)).thenReturn(expected);

        Message actual = messageService.save(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void delete() {
        Message expected=new Message();
        when(messageRepository.isExistById(expected.getId())).thenReturn(true);

        Message actual=messageService.delete(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }
}