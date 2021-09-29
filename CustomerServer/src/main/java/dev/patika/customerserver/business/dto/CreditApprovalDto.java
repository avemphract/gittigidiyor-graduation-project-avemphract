package dev.patika.customerserver.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApprovalDto extends BaseDto{
    private long id;
    @ApiModelProperty(example = "true")
    private boolean isApproval;
    @ApiModelProperty(example = "1000")
    private double givenCreditAmount;
    @ApiModelProperty(example = "10000000000")
    private long customerTcNumber;

}
