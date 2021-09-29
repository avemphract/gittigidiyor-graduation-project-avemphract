package dev.patika.customerserver.business.mapper;

import dev.patika.customerserver.business.dto.CreditApprovalDto;
import dev.patika.customerserver.business.service.CreditApprovalService;
import dev.patika.customerserver.business.service.CustomerService;
import dev.patika.customerserver.entities.CreditApproval;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CreditApprovalMapper {
    @Autowired
    protected CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Mappings({
            @Mapping(target = "customerTcNumber",expression = "java(creditApproval.getCustomer().getTcNumber())")
    })
    public abstract CreditApprovalDto toDto(CreditApproval creditApproval);
    @Mappings({
            @Mapping(target = "customer",expression = "java(customerService.findByTcNumber(creditApprovalDto.getCustomerTcNumber()))")
    })
    public abstract CreditApproval toEntity(CreditApprovalDto creditApprovalDto);
}
