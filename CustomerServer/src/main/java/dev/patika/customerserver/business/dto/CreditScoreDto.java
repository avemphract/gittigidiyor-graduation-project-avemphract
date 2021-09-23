package dev.patika.customerserver.business.dto;


import lombok.Data;

@Data
public class CreditScoreDto extends BaseDto {
    private long tcNumber;
    private double creditScore;
}

