package com.example.CloudBalance_Backend.Service.Implementation;

import com.example.CloudBalance_Backend.DTO.AccountDTO;
import com.example.CloudBalance_Backend.Mapper.AccountMapper;
import com.example.CloudBalance_Backend.Model.Account;
import com.example.CloudBalance_Backend.Repository.AccountRepository;
import com.example.CloudBalance_Backend.Service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(AccountDTO dto) {
        Account account = accountMapper.map(dto);
        return accountRepository.save(account);
    }

    @Override
    public AccountDTO  getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        return accountMapper.map(account);
    }

    @Transactional
    @Override
    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        accountMapper.updateEntityFromDto(dto, existingAccount);

        Account updated = accountRepository.save(existingAccount);
        return accountMapper.map(updated);
    }
}

