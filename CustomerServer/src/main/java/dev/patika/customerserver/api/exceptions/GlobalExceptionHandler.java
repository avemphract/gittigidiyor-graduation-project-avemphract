package dev.patika.customerserver.api.exceptions;

import dev.patika.customerserver.business.dto.ErrorDto;
import dev.patika.customerserver.business.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ErrorService errorService;

    @Autowired
    public GlobalExceptionHandler(ErrorService errorService) {
        this.errorService = errorService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleException(EntityAlreadyExistsException e) {
        return new ResponseEntity<>(errorService.saveException(e, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException e) {
        return new ResponseEntity<>(errorService.saveException(e, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidCustomerNameException.class)
    public ResponseEntity<ErrorDto> handleException(NotValidCustomerNameException e) {
        return new ResponseEntity<>(errorService.saveException(e, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidPhoneNumberException.class)
    public ResponseEntity<ErrorDto> handleException(NotValidPhoneNumberException e) {
        return new ResponseEntity<>(errorService.saveException(e, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidTcNumberException.class)
    public ResponseEntity<ErrorDto> handleException(NotValidTcNumberException e) {
        return new ResponseEntity<>(errorService.saveException(e,HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({ConnectException.class,feign.FeignException.ServiceUnavailable.class})
    public ResponseEntity<ErrorDto> handleException(Exception e){
        return new ResponseEntity<>(errorService.saveException(new ServerConnectionException(),HttpStatus.BAD_GATEWAY),HttpStatus.BAD_GATEWAY);
    }
}
