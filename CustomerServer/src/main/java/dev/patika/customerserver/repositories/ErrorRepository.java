package dev.patika.customerserver.repositories;

import dev.patika.customerserver.entities.ErrorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends CrudRepository<ErrorEntity,Long> {

}
