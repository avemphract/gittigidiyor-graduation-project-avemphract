package dev.patika.customerserver.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ToString(exclude = {"creditApprovals"})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    private long tcNumber;
    private String name;
    private String surname;
    private double salary;
    private long phoneNumber;

    @OneToMany(mappedBy = "customer")
    @Cascade(CascadeType.DELETE)
    private final Set<CreditApproval> creditApprovals = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return getId()==customer.getId() && tcNumber == customer.tcNumber && Double.compare(customer.salary, salary) == 0 && phoneNumber == customer.phoneNumber && name.equalsIgnoreCase(customer.name) && surname.equalsIgnoreCase(customer.surname);
    }
}
