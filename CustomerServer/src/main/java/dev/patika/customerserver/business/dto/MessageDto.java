package dev.patika.customerserver.business.dto;

import dev.patika.customerserver.entities.enums.MessageType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageDto extends BaseDto {
    private long id;
    @ApiModelProperty(example = "CREDIT_APPROVAL_RESULT")
    private MessageType type;
    @ApiModelProperty(example = "5079064664")
    private long toPhone;
    private String context;
}
