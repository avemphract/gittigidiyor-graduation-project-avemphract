package dev.patika.customerserver.business.service;

import dev.patika.customerserver.api.exceptions.EntityAlreadyExistsException;
import dev.patika.customerserver.api.exceptions.EntityNotFoundException;
import dev.patika.customerserver.business.dto.MessageDto;
import dev.patika.customerserver.business.mapper.MessageMapper;
import dev.patika.customerserver.entities.Message;
import dev.patika.customerserver.repositories.MessageRepository;
import dev.patika.customerserver.utils.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements BaseService<MessageDto,Message> {
    private final MessageRepository messageRepository;
    private final MessageFactory messageFactory;
    @Autowired
    private MessageMapper mapper;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageFactory messageFactory) {
        this.messageRepository = messageRepository;
        this.messageFactory=messageFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> findAll() {
        List<Message> messageList=new ArrayList<>();
        messageRepository.findAll().forEach(messageList::add);
        return messageList;
    }

    @Override
    @Transactional(readOnly = true)
    public Message findById(long id) {
        Optional<Message> optional=messageRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    @Transactional
    public Message save(Message object) {
        if (messageRepository.isExistById(object.getId()))
            throw new EntityAlreadyExistsException(object.getId()+" id is already registered in message table");
        return messageRepository.save(object);
    }

    @Override
    @Transactional
    public Message update(Message object) {
        if (!messageRepository.isExistById(object.getId()))
            throw new EntityNotFoundException(object.getId()+" id has not found in message table");
        return messageRepository.save(object);
    }

    @Override
    @Transactional
    public Message delete(Message object) {
        if (!messageRepository.isExistById(object.getId()))
            throw new EntityNotFoundException(object.getId()+" id has not found in message table");
        messageRepository.delete(object);
        return object;
    }

    @Override
    public MessageDto toDto(Message message) {
        return mapper.toDto(message);
    }

    @Override
    public Message toEntity(MessageDto messageDto) {
        return mapper.toEntity(messageDto);
    }
}
