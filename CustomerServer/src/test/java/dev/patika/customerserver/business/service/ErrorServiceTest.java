package dev.patika.customerserver.business.service;

import dev.patika.customerserver.entities.ErrorEntity;
import dev.patika.customerserver.repositories.ErrorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ErrorServiceTest {

    @Mock
    private ErrorRepository errorRepository;

    @InjectMocks
    private ErrorService errorService;

    @Test
    void findAll() {
        List<ErrorEntity> expected = new ArrayList<>();
        when(errorRepository.findAll()).thenReturn(expected);

        List<ErrorEntity> actual = errorService.findAll();

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected.size(),actual.size()),
                ()->assertEquals(expected,actual)
        );
    }
}