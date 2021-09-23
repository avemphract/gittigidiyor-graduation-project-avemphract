package dev.patika.creditscoreserver.business.mappers;

import dev.patika.creditscoreserver.business.dto.CreditScoreDto;
import dev.patika.creditscoreserver.entities.CreditScore;
import org.mapstruct.Mapper;

@Mapper
public abstract class CreditScoreMapper {
    public abstract CreditScoreDto toDto(CreditScore creditScore);
    public abstract CreditScore toEntity(CreditScoreDto creditScoreDto);
}
