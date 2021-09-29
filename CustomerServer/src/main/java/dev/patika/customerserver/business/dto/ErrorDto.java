package dev.patika.customerserver.business.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorDto extends BaseDto{

    private Instant createDate;

    private int errorCode;
    private String errorMessage;

    private String errorUrl;
}
