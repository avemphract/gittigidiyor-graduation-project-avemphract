package dev.patika.creditscoreserver.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreDto extends BaseDto{
    private long tcNumber;
    private double creditScore;
}
