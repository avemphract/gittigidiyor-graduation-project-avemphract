package dev.patika.customerserver.business.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerDto extends BaseDto{
    private long tcNumber;
    private String name;
    private String surname;
    private double salary;
    private long phoneNumber;

    private final Set<Long> creditApprovalsId = new HashSet<>();
}
