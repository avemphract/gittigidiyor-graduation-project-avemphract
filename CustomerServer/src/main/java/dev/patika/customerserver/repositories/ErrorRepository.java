package dev.patika.customerserver.repositories;

import dev.patika.customerserver.entities.ErrorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends MongoRepository<ErrorEntity,String> {

}
