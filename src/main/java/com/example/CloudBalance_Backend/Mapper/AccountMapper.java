package com.example.CloudBalance_Backend.Mapper;

import com.example.CloudBalance_Backend.DTO.AccountDTO;
import com.example.CloudBalance_Backend.Model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {


    public AccountDTO map(Account entity) {
        AccountDTO dto = new AccountDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setArn(entity.getArn());
        dto.setAccountId(entity.getAccountId());
        return dto;
    }

    public Account map(AccountDTO dto) {
        Account entity = new Account();
        entity.setId(dto.getId());
        entity.setAccountId(dto.getAccountId());
        entity.setArn(dto.getArn());
        entity.setName(dto.getName());
        return entity;
    }

    public void updateEntityFromDto(AccountDTO dto, Account entity) {
        entity.setId(dto.getId());
        entity.setAccountId(dto.getAccountId());
        entity.setArn(dto.getArn());
        entity.setName(dto.getName());
    }
}