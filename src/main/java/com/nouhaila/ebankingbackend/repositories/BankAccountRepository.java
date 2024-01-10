package com.nouhaila.ebankingbackend.repositories;

import com.nouhaila.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
