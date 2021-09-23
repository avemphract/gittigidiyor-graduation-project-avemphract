package dev.patika.creditscoreserver.business.services;

import dev.patika.creditscoreserver.business.dto.BaseDto;
import dev.patika.creditscoreserver.entities.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public interface BaseService<T extends BaseDto,E extends BaseEntity> {
    List<E> findAll();
    E findById(long id);

    E save(E object);
    E update(E object);

    E delete(E object);

    T toDto(E e);
    default List<T> toDto(List<E> list){
        List<T> result=new ArrayList<>();
        list.forEach(this::toDto);
        return result;
    }
    E toEntity(T t);

    default List<E> toEntity(List<T> list){
        List<E> result=new ArrayList<>();
        list.forEach(this::toEntity);
        return result;
    }
}
