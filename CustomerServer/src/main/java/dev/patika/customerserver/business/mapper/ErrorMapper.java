package dev.patika.customerserver.business.mapper;

import dev.patika.customerserver.business.dto.ErrorDto;
import dev.patika.customerserver.entities.ErrorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ErrorMapper {

    public abstract ErrorEntity toEntity(ErrorDto errorDto);

    public abstract ErrorDto toDto(ErrorEntity errorEntity);
}
