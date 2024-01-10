package com.nouhaila.ebankingbackend.mappers;

import com.nouhaila.ebankingbackend.dtos.AccountOperationDTO;
import com.nouhaila.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.nouhaila.ebankingbackend.dtos.CustomersDTO;
import com.nouhaila.ebankingbackend.dtos.SavingBankAccountDTO;
import com.nouhaila.ebankingbackend.entities.AccountOperation;
import com.nouhaila.ebankingbackend.entities.CurrentAccount;
import com.nouhaila.ebankingbackend.entities.Customer;
import com.nouhaila.ebankingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomersDTO fromCustomer(Customer customer){
        CustomersDTO customersDTO=new CustomersDTO();
        BeanUtils.copyProperties(customer,customersDTO);
        return customersDTO;
    }
    public Customer fromCustomerDTO(CustomersDTO customersDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customersDTO,customer);
        return customer;
    }
    public SavingBankAccountDTO fromSavingBankAccount  (SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
        savingBankAccountDTO.setCustomersDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }
    public SavingAccount fromSavingBankAccountDTO  (SavingBankAccountDTO savingBankAccountDTO){
    SavingAccount savingAccount=new SavingAccount();
    BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
    savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomersDTO()));
    return savingAccount;
    }
    public CurrentBankAccountDTO fromCurrentBankAccount  (CurrentAccount currentAccount){
    CurrentBankAccountDTO currentBankAccountDTO=new CurrentBankAccountDTO();
    BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
    currentBankAccountDTO.setCustomersDTO(fromCustomer(currentAccount.getCustomer()));
    currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());

        return currentBankAccountDTO;
    }
    public CurrentAccount fromCurrentBankAccountDTO  (CurrentBankAccountDTO currentBankAccountDTO){
     CurrentAccount currentAccount=new CurrentAccount();
     BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
     currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomersDTO()));
     return currentAccount;
    }
    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
      AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
      BeanUtils.copyProperties(accountOperation,accountOperationDTO);
      return accountOperationDTO;
    }



}
