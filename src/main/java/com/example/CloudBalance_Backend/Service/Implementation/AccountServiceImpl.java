package com.example.CloudBalance_Backend.Service.Implementation;

import com.example.CloudBalance_Backend.DTO.AccountDTO;
import com.example.CloudBalance_Backend.Mapper.AccountMapper;
import com.example.CloudBalance_Backend.Model.Account;
import com.example.CloudBalance_Backend.Model.User;
import com.example.CloudBalance_Backend.Repository.AccountRepository;
import com.example.CloudBalance_Backend.Repository.UserRepository;
import com.example.CloudBalance_Backend.Service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userrepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        userrepository = userRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isCustomer = authentication
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"));

        if (isCustomer) {
            String email = authentication.getName();
            User entity = userrepository.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
            Set<Account> accounts = entity.getAccounts();
            return new ArrayList<>(accounts);
        } else {
            return accountRepository.findAll();
        }
    }

    @Override
    public Account createAccount(AccountDTO dto) {
        Account account = accountMapper.map(dto);
        return accountRepository.save(account);
    }

    @Override
    public String getArnByAccountId(Long accountId) {
        return accountRepository.findByAccountId(accountId)
                .map(Account::getArn)
                .orElseThrow(() -> new RuntimeException("Account not found with accountId: " + accountId));
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

