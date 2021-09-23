package dev.patika.customerserver.business.mapper;

import dev.patika.customerserver.business.dto.MessageDto;
import dev.patika.customerserver.business.service.MessageService;
import dev.patika.customerserver.entities.Message;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class MessageMapper {
    public abstract MessageDto toDto(Message message);
    public abstract Message toEntity(MessageDto messageDto);

}
