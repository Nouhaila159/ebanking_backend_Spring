package com.nouhaila.ebankingbackend.dtos;

import com.nouhaila.ebankingbackend.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operatinDate;
    private double amount;
    private OperationType type;
    private String description;
}
