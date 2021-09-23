package dev.patika.customerserver.business.mapper;

import dev.patika.customerserver.business.dto.CustomerDto;
import dev.patika.customerserver.business.service.CreditApprovalService;
import dev.patika.customerserver.entities.BaseEntity;
import dev.patika.customerserver.entities.CreditApproval;
import dev.patika.customerserver.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {
    @Autowired
    protected CreditApprovalService creditApprovalService;

    @Autowired
    protected CreditApprovalMapper creditApprovalMapper;

    @Mappings({
            @Mapping(target = "creditApprovalsId",expression = "java(getCreditApprovalsId(customer))")
    })
    public abstract CustomerDto toDto(Customer customer);
    @Mappings({
            @Mapping(target = "creditApprovals",expression = "java(getCreditApprovals(customerDto))")
    })
    public abstract Customer toEntity(CustomerDto customerDto);


    protected Set<CreditApproval> getCreditApprovals(CustomerDto customerDto){
        return customerDto.getCreditApprovalsId().stream().map(aLong -> creditApprovalService.findById(aLong)).collect(Collectors.toSet());
    }

    protected Set<Long> getCreditApprovalsId(Customer customer){
        return customer.getCreditApprovals().stream().map(BaseEntity::getId).collect(Collectors.toSet());
    }

}
