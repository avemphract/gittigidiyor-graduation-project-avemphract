package dev.patika.customerserver.repositories;

import dev.patika.customerserver.entities.CreditApproval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditApprovalRepository extends CrudRepository<CreditApproval, Long> {

    @Query("SELECT CASE WHEN COUNT(c)>0 THEN TRUE ELSE FALSE END " +
            "FROM CreditApproval AS c " +
            "WHERE c.id=?1")
    boolean isExistById(long id);

    @Query("SELECT c FROM CreditApproval AS c WHERE c.customer.tcNumber=?1")
    List<CreditApproval> findByCustomerTc(long tc);
}
