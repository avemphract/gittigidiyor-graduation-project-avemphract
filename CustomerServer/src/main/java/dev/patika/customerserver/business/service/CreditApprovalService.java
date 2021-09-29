package dev.patika.customerserver.business.service;

import dev.patika.customerserver.api.exceptions.CreditApplicationCustomerNullException;
import dev.patika.customerserver.api.exceptions.EntityAlreadyExistsException;
import dev.patika.customerserver.api.exceptions.EntityNotFoundException;
import dev.patika.customerserver.business.dto.CreditApprovalDto;
import dev.patika.customerserver.business.mapper.CreditApprovalMapper;
import dev.patika.customerserver.entities.CreditApproval;
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
public class CreditApprovalService implements BaseService<CreditApprovalDto,CreditApproval,Long> {
    private final CreditApprovalRepository creditApprovalRepository;
    private final MessageService messageService;
    private final MessageFactory messageFactory;
    private final RequestInfo requestInfo;

    @Autowired
    private CreditApprovalMapper mapper;

    @Autowired
    public CreditApprovalService(CreditApprovalRepository creditApprovalRepository, MessageService messageService, MessageFactory messageFactory, RequestInfo requestInfo) {
        this.creditApprovalRepository = creditApprovalRepository;
        this.messageService=messageService;
        this.messageFactory=messageFactory;
        this.requestInfo=requestInfo;
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
    public CreditApproval findById(Long id) {
        Optional<CreditApproval> optional=creditApprovalRepository.findById(id);
        return optional.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<CreditApproval> findByCustomer(Long tc) {
        return creditApprovalRepository.findByCustomerTc(tc);
    }

    @Override
    @Transactional
    public CreditApproval save(CreditApproval creditApproval) {
        if (creditApprovalRepository.isExistById(creditApproval.getId()))
            throw new EntityAlreadyExistsException(creditApproval.getId()+" id is already registered in creditApproval table");
        if (creditApproval.getCustomer()==null)
            throw new CreditApplicationCustomerNullException("Credit application must have a customer");
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
        if (creditApproval.getCustomer()==null)
            throw new CreditApplicationCustomerNullException("Credit application must have a customer");
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
