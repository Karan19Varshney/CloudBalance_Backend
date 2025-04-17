package com.example.CloudBalance_Backend.Mapper;

import com.example.CloudBalance_Backend.DTO.User_DTO;
import com.example.CloudBalance_Backend.Model.Account;
import com.example.CloudBalance_Backend.Model.ERole;
import com.example.CloudBalance_Backend.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private AccountMapper accountMapper;

    public User_DTO toDto(User user) {
        User_DTO dto = new User_DTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRole(user.getRole().name());
        dto.setPassword(user.getPassword());
        dto.setLastLogin(user.getLast_Login());

        if (user.getRole().equals(ERole.CUSTOMER) && user.getAccounts() != null) {
            Set<Long> ids = user.getAccounts().stream()
                    .map(Account::getId)
                    .collect(Collectors.toSet());
            dto.setAccountIds(ids);
        }
        return dto;
    }
}