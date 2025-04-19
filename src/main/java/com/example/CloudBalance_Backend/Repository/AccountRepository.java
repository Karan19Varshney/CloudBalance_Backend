package com.example.CloudBalance_Backend.Repository;

import com.example.CloudBalance_Backend.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}