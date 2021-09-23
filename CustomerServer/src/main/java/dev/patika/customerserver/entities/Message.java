package dev.patika.customerserver.entities;

import dev.patika.customerserver.entities.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private long toPhone;
    private String context;
}
