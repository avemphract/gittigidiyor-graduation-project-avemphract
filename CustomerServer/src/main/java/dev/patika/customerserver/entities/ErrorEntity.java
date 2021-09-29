package dev.patika.customerserver.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.Instant;

@Data
@EntityListeners(AuditingEntityListener.class)
@Document
public class ErrorEntity {
    @CreatedDate
    private Instant createDate=Instant.now();

    private int errorCode;
    private String errorMessage;

    private String errorUrl;
}
