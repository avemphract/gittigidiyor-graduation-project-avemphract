package dev.patika.customerserver.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CreditApproval extends BaseEntity {
    private boolean isApproval;
    private double givenCreditAmount;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Customer customer;

    private String sessionId;
    private String clientUrl;
    private String requestURI;
}
