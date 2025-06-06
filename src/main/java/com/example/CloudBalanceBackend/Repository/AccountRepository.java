package com.example.CloudBalanceBackend.Repository;

import com.example.CloudBalanceBackend.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(Long accountId);
}