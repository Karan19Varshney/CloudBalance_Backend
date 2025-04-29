package com.example.CloudBalanceBackend.Service;

import com.example.CloudBalanceBackend.DTO.AccountDTO;
import com.example.CloudBalanceBackend.Model.Account;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    AccountDTO getAccountById(Long id);
    AccountDTO updateAccount(Long id, AccountDTO dto);
    Account createAccount(AccountDTO dto);
    String getArnByAccountId(Long accountId);
}
