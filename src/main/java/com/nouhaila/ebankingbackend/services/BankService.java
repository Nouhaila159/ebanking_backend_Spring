package com.nouhaila.ebankingbackend.services;

import com.nouhaila.ebankingbackend.entities.BankAccount;
import com.nouhaila.ebankingbackend.entities.CurrentAccount;
import com.nouhaila.ebankingbackend.entities.SavingAccount;
import com.nouhaila.ebankingbackend.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepository.findById("283eba75-7e42-427e-8420-f0970b509513").orElse(null);

        if(bankAccount!=null) {
            System.out.println("********************** View account *********************");
            System.out.println("id :" +bankAccount.getId());
            System.out.println("Balance :" +bankAccount.getBalance());
            System.out.println("status :" +bankAccount.getStatus());
            System.out.println("Creation date :" +bankAccount.getCreatedAt());
            System.out.println("Client name :"+bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over Draft=>" + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate" + ((SavingAccount) bankAccount).getIntersetRate());
            }
            System.out.println("================= Operations ======================");
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
            });
        }
    }
}
