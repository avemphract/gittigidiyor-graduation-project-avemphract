package dev.patika.customerserver.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ErrorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    private Instant createDate=Instant.now();

    private int errorCode;
    private String errorMessage;

    private String errorUrl;
}
