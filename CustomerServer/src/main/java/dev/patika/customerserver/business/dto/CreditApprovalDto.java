package dev.patika.customerserver.business.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CreditApprovalDto extends BaseDto{
    private long id;
    private boolean isApproval;
    private double givenCreditAmount;
    private long customerTcNumber;
}
