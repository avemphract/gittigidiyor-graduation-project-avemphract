package dev.patika.customerserver.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"creditApprovals"})
@ToString(exclude = {"creditApprovals"})
@Entity
@Data
public class Customer extends BaseEntity {
    private long tcNumber;
    private String name;
    private String surname;
    private double salary;
    private long phoneNumber;

    @OneToMany(mappedBy = "customer")
    @Cascade(CascadeType.DELETE)
    private final Set<CreditApproval> creditApprovals = new HashSet<>();
}
