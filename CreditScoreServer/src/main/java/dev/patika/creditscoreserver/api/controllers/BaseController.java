package dev.patika.creditscoreserver.api.controllers;

import dev.patika.creditscoreserver.business.dto.BaseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T extends BaseDto,I extends Number> {

    ResponseEntity<List<T>> findAll();

    ResponseEntity<T> findById(I id);

    ResponseEntity<T> save(T body);

    ResponseEntity<T> update(T body);

    ResponseEntity<T> delete(T body);
}
