package dev.patika.creditscoreserver.repositories;

import dev.patika.creditscoreserver.entities.CreditScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditScoreRepository extends CrudRepository<CreditScore,Long> {

    @Query("SELECT c FROM CreditScore As c WHERE c.tcNumber=?1")
    CreditScore findByTcNumber(long tcNumber);

    @Query("SELECT CASE WHEN COUNT(c)>0 THEN TRUE ELSE FALSE END " +
            "FROM CreditScore AS c " +
            "WHERE c.tcNumber=?1")
    boolean isExistByTcNumber(long tcNumber);
}
