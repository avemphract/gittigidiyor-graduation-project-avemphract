package dev.patika.customerserver.repositories;

import dev.patika.customerserver.entities.Message;
import dev.patika.customerserver.entities.enums.MessageType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message,Long> {

    @Query("SELECT CASE WHEN COUNT(m)>0 THEN TRUE ELSE FALSE END " +
            "FROM Message AS m " +
            "WHERE m.id=?1")
    boolean isExistById(long id);

    @Query("SELECT m FROM Message AS m WHERE m.type=?1")
    List<Message> findByMessageType(MessageType messageType);


}
