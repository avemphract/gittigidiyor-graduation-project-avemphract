package dev.patika.customerserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApproval extends BaseEntity {
    private boolean isApproval;
    private double givenCreditAmount;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Customer customer;

    private String sessionId;
    private String clientUrl;
    private String requestURI;

    public CreditApproval(Customer customer) {
        this.customer = customer;
    }

    public CreditApproval(boolean isApproval, double givenCreditAmount, Customer customer) {
        this.isApproval = isApproval;
        this.givenCreditAmount = givenCreditAmount;
        this.customer = customer;
    }
}
