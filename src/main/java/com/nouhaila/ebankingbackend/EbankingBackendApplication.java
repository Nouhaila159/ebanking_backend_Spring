package com.nouhaila.ebankingbackend;

import com.nouhaila.ebankingbackend.dtos.BankAccountDTO;
import com.nouhaila.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.nouhaila.ebankingbackend.dtos.CustomersDTO;
import com.nouhaila.ebankingbackend.dtos.SavingBankAccountDTO;
import com.nouhaila.ebankingbackend.entities.*;
import com.nouhaila.ebankingbackend.enums.AccountStatus;
import com.nouhaila.ebankingbackend.enums.OperationType;
import com.nouhaila.ebankingbackend.exceptions.CustomerNotFoundException;
import com.nouhaila.ebankingbackend.repositories.AccountOperationRepository;
import com.nouhaila.ebankingbackend.repositories.BankAccountRepository;
import com.nouhaila.ebankingbackend.repositories.CustomerRepository;
import com.nouhaila.ebankingbackend.services.BankAccountService;
import com.nouhaila.ebankingbackend.entities.AccountOperation;
import com.nouhaila.ebankingbackend.entities.CurrentAccount;
import com.nouhaila.ebankingbackend.entities.Customer;
import com.nouhaila.ebankingbackend.entities.SavingAccount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication

public class EbankingBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(EbankingBackendApplication.class, args);

	}
@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
		return args -> {
			Stream.of("Nouhaila","Fatima","Ohtman").forEach(
					name->{
						CustomersDTO customer=new CustomersDTO();
						customer.setName(name);
						customer.setEmail(name+"@gmail.com");
					   bankAccountService.saveCustomer(customer);

					});
			bankAccountService.listCustomers().forEach(customer -> {
				try {
					bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
				    bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				}

			});
			List<BankAccountDTO> bankAccounts=bankAccountService.bankAccountList();
			for(BankAccountDTO bankAccount:bankAccounts) {
				for (int i = 0; i < 10; i++) {
					String accountId;
					if(bankAccount instanceof SavingBankAccountDTO){
						accountId=((SavingBankAccountDTO) bankAccount).getId();
					}else {
						accountId=((CurrentBankAccountDTO) bankAccount).getId();
					}
					bankAccountService.credit(accountId, 10000 + Math.random() * 120000, "Credit");
					bankAccountService.debit(accountId, 10000 + Math.random() * 9000, "Debit");
				}
			}
			};
	}
	//@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository accountOperationRepository){
		return args -> {
			Stream.of("nouhaila","ohtman","fatima").forEach(name->{
				Customer customer=new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(customer -> {
				CurrentAccount currentAccount=new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREADTED);
				currentAccount.setCustomer(customer);
				currentAccount.setOverDraft(9000);
				bankAccountRepository.save(currentAccount);
				SavingAccount savingAccount=new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREADTED);
				savingAccount.setCustomer(customer);
				savingAccount.setIntersetRate(5.5);
				bankAccountRepository.save(savingAccount);
			});
			bankAccountRepository.findAll().forEach(acc->{
				for(int i=0 ;i<10;i++ ){
					AccountOperation accountOperation=new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*12000);
					accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
					accountOperation.setBankAccount(acc);
					accountOperationRepository.save(accountOperation);
				}

			});
		};
	}

}
