package com.example.CloudBalance_Backend.Service.Implementation;

import com.example.CloudBalance_Backend.DTO.User_DTO;
import com.example.CloudBalance_Backend.Mapper.UserMapper;
import com.example.CloudBalance_Backend.Model.Account;
import com.example.CloudBalance_Backend.Model.ERole;
import com.example.CloudBalance_Backend.Model.User;
import com.example.CloudBalance_Backend.Repository.AccountRepository;
import com.example.CloudBalance_Backend.Repository.UserRepository;
import com.example.CloudBalance_Backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User_DTO createUser(User_DTO userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        ERole role = ERole.valueOf(userDto.getRole().toUpperCase());

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(role);

        if (role.equals(ERole.CUSTOMER)) {
            if (userDto.getAccountIds() != null && !userDto.getAccountIds().isEmpty()) {
                List<Account> accounts = accountRepository.findAllById(userDto.getAccountIds());

                if (accounts.size() != userDto.getAccountIds().size()) {
                    throw new IllegalArgumentException("Some provided account IDs are invalid.");
                }

                user.setAccounts(new HashSet<>(accounts));
            }
        } else if (userDto.getAccountIds() != null && !userDto.getAccountIds().isEmpty()) {
            throw new IllegalArgumentException("Only CUSTOMER users can be linked to accounts.");
        }

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public List<User_DTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User_DTO updateUser(Long userId, User_DTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setRole(ERole.valueOf(dto.getRole().toUpperCase()));
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getAccountIds() != null) {
            if (dto.getAccountIds().isEmpty()) {
                user.setAccounts(null); // Clear assigned accounts
            } else {
                Set<Account> assignedAccounts = dto.getAccountIds().stream()
                        .map(accountId -> accountRepository.findById(accountId)
                                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId)))
                        .collect(Collectors.toSet());
                user.setAccounts(assignedAccounts);
            }
        }

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public User_DTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return userMapper.toDto(user);
    }
}