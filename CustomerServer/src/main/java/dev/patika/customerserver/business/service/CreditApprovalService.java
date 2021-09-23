package dev.patika.customerserver.business.service;

import dev.patika.customerserver.api.exceptions.EntityAlreadyExistsException;
import dev.patika.customerserver.api.exceptions.EntityNotFoundException;
import dev.patika.customerserver.business.dto.BaseDto;
import dev.patika.customerserver.business.dto.CreditApprovalDto;
import dev.patika.customerserver.business.dto.MessageDto;
import dev.patika.customerserver.business.mapper.CreditApprovalMapper;
import dev.patika.customerserver.business.mapper.MessageMapper;
import dev.patika.customerserver.entities.BaseEntity;
import dev.patika.customerserver.entities.CreditApproval;
import dev.patika.customerserver.entities.Message;
import dev.patika.customerserver.repositories.CreditApprovalRepository;
import dev.patika.customerserver.utils.MessageFactory;
import dev.patika.customerserver.utils.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditApprovalService implements BaseService<CreditApprovalDto,CreditApproval> {
    private final CreditApprovalRepository creditApprovalRepository;
    private final MessageService messageService;
    private final MessageFactory messageFactory;

    @Autowired
    private CreditApprovalMapper mapper;
    @Autowired
    private RequestInfo requestInfo;

    @Autowired
    public CreditApprovalService(CreditApprovalRepository creditApprovalRepository, MessageService messageService, MessageFactory messageFactory) {
        this.creditApprovalRepository = creditApprovalRepository;
        this.messageService=messageService;
        this.messageFactory=messageFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditApproval> findAll() {
        List<CreditApproval> creditApproval=new ArrayList<>();
        creditApprovalRepository.findAll().forEach(creditApproval::add);
        return creditApproval;
    }

    @Override
    @Transactional(readOnly = true)
    public CreditApproval findById(long id) {
        Optional<CreditApproval> optional=creditApprovalRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    @Transactional
    public CreditApproval save(CreditApproval creditApproval) {
        if (creditApprovalRepository.isExistById(creditApproval.getId()))
            throw new EntityAlreadyExistsException(creditApproval.getId()+" id is already registered in creditApproval table");
        messageService.save(messageFactory.createCreditApprovalMessage(creditApproval.isApproval(), creditApproval.getCustomer().getPhoneNumber(),creditApproval.getGivenCreditAmount()));
        creditApproval.setClientUrl(requestInfo.getClientUrl());
        creditApproval.setSessionId(requestInfo.getSessionId());
        creditApproval.setRequestURI(requestInfo.getRequestURI());
        return creditApprovalRepository.save(creditApproval);
    }

    @Override
    @Transactional
    public CreditApproval update(CreditApproval creditApproval) {
        if (!creditApprovalRepository.isExistById(creditApproval.getId()))
            throw new EntityNotFoundException(creditApproval.getId()+" id has not found in creditApproval table");
        return creditApprovalRepository.save(creditApproval);
    }

    @Override
    @Transactional
    public CreditApproval delete(CreditApproval creditApproval) {
        if (!creditApprovalRepository.isExistById(creditApproval.getId()))
            throw new EntityNotFoundException(creditApproval.getId()+" id has not found in creditApproval table");
        creditApprovalRepository.delete(creditApproval);
        return creditApproval;
    }

    @Override
    public CreditApprovalDto toDto(CreditApproval baseEntity) {
        return mapper.toDto(baseEntity);
    }

    @Override
    public CreditApproval toEntity(CreditApprovalDto baseDto) {
        return mapper.toEntity(baseDto);
    }
}
