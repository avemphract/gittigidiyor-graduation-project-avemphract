package dev.patika.customerserver.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerDto extends BaseDto{
    @ApiModelProperty(example = "10000000000")
    private long tcNumber;
    @ApiModelProperty(example = "Emre")
    private String name;
    @ApiModelProperty(example = "Çatalkaya")
    private String surname;
    @ApiModelProperty(example = "6000")
    private double salary;
    @ApiModelProperty(example = "5079064664")
    private long phoneNumber;

    private final Set<Long> creditApprovalsId = new HashSet<>();
}
