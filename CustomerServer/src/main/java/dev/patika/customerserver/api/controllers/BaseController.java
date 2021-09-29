package dev.patika.customerserver.api.controllers;

import dev.patika.customerserver.business.dto.BaseDto;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T extends BaseDto,I> {

    ResponseEntity<List<T>> findAll();

    ResponseEntity<T> findById(I id);

    default ResponseEntity<T> save(T body){
        throw new NotImplementedException();
    }

    default ResponseEntity<T> update(T body){
        throw new NotImplementedException();
    }

    default ResponseEntity<T> delete(T body){
        throw new NotImplementedException();
    };
}