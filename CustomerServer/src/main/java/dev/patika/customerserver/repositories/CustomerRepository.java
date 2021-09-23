package dev.patika.customerserver.repositories;

import dev.patika.customerserver.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    @Query("SELECT CASE WHEN COUNT(c)>0 THEN TRUE ELSE FALSE END " +
            "FROM Customer AS c " +
            "WHERE c.tcNumber=?1")
    boolean isExistByTckn(long tckn);

    @Query("SELECT c FROM Customer AS c WHERE c.tcNumber=?1")
    Customer findByTckn(long tckn);
}
