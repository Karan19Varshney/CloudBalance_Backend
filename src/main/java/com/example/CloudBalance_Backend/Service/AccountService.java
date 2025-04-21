package com.example.CloudBalance_Backend.Service;

import com.example.CloudBalance_Backend.DTO.AccountDTO;
import com.example.CloudBalance_Backend.Model.Account;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    AccountDTO getAccountById(Long id);
    AccountDTO updateAccount(Long id, AccountDTO dto);
    Account createAccount(AccountDTO dto);
    String getArnByAccountId(Long accountId);
}
