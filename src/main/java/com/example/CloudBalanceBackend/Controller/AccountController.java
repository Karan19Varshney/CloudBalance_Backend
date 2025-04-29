package com.example.CloudBalanceBackend.Controller;

import com.example.CloudBalanceBackend.DTO.AccountDTO;
import com.example.CloudBalanceBackend.Model.Account;
import com.example.CloudBalanceBackend.Service.Implementation.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','READ_ONLY','CUSTOMER')")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        Account createdAccount = accountService.createAccount(accountDTO);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

}
