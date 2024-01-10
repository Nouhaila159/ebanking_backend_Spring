package com.nouhaila.ebankingbackend.dtos;
import com.nouhaila.ebankingbackend.enums.AccountStatus;
import lombok.Data;
import java.util.Date;



@Data

public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomersDTO customersDTO;
   private double interestRate ;


}
