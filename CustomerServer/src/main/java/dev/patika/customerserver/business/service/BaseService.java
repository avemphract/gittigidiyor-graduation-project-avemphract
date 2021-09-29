package dev.patika.customerserver.business.service;


import dev.patika.customerserver.business.dto.BaseDto;
import org.apache.commons.lang.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public interface BaseService<T extends BaseDto,E,I> {
    List<E> findAll();
    E findById(I id);

    E save(E object);

    default E update(E object){
        throw new NotImplementedException();
    }

    default E delete(E object){
        throw new NotImplementedException();
    }

    T toDto(E e);

    default List<T> toDto(List<E> list){
        List<T> result=new ArrayList<>();
        list.forEach(l->result.add(toDto(l)));
        return result;
    }

    E toEntity(T t);

    default List<E> toEntity(List<T> list){
        List<E> result=new ArrayList<>();
        list.forEach(l->result.add(toEntity(l)));
        return result;
    }
}
