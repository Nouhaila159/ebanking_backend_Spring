package com.nouhaila.ebankingbackend.services;

import com.nouhaila.ebankingbackend.dtos.*;
import com.nouhaila.ebankingbackend.dtos.*;
import com.nouhaila.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.nouhaila.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.nouhaila.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomersDTO saveCustomer(CustomersDTO customersDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;

    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double inerestRate, Long customerId) throws CustomerNotFoundException;

    List<CustomersDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit (String accountId ,double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit (String accountId, double amount, String description) throws BalanceNotSufficientException, BankAccountNotFoundException;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    CustomersDTO getCustomer(Long customerId)throws CustomerNotFoundException;

    CustomersDTO updateCustomer(CustomersDTO customersDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO>accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomersDTO> searchCustomers(String keyword);
}
