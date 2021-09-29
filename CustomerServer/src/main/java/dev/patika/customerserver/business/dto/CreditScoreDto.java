package dev.patika.customerserver.business.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditScoreDto extends BaseDto {
    private long tcNumber;
    private double creditScore;
}

