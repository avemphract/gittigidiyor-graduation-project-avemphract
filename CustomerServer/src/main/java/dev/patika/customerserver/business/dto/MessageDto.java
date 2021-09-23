package dev.patika.customerserver.business.dto;

import dev.patika.customerserver.entities.enums.MessageType;
import lombok.Data;

@Data
public class MessageDto extends BaseDto {
    private long id;
    private MessageType type;
    private long toPhone;
    private String context;
}
