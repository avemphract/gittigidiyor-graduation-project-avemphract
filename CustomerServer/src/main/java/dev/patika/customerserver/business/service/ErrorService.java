package dev.patika.customerserver.business.service;

import dev.patika.customerserver.business.dto.ErrorDto;
import dev.patika.customerserver.business.mapper.ErrorMapper;
import dev.patika.customerserver.entities.ErrorEntity;
import dev.patika.customerserver.repositories.ErrorRepository;
import dev.patika.customerserver.utils.RequestInfo;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ErrorService implements BaseService<ErrorDto, ErrorEntity,String> {

    @Autowired
    private ErrorMapper errorMapper;

    private final ErrorRepository errorRepository;
    private final RequestInfo requestInfo;

    public ErrorService(ErrorRepository errorRepository, RequestInfo requestInfo) {
        this.errorRepository = errorRepository;
        this.requestInfo=requestInfo;
    }

    @Override
    public List<ErrorEntity> findAll() {
        return new ArrayList<>(errorRepository.findAll());
    }

    @Override
    public ErrorEntity findById(String id) {
        return errorRepository.findById(id).orElse(null);
    }

    @Override
    public ErrorEntity save(ErrorEntity object) {
        return errorRepository.save(object);
    }


    public ErrorDto saveException(Exception e,HttpStatus httpStatus) {
        ErrorEntity errorEntity=new ErrorEntity();
        errorEntity.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(e.getMessage());
        errorEntity.setErrorUrl(requestInfo.getRequestURI());
        errorEntity.setErrorCode(httpStatus.value());
        return toDto(save(errorEntity));
    }

    @Override
    public ErrorDto toDto(ErrorEntity errorEntity) {
        return errorMapper.toDto(errorEntity);
    }

    @Override
    public ErrorEntity toEntity(ErrorDto errorDto) {
        return errorMapper.toEntity(errorDto);
    }
}
