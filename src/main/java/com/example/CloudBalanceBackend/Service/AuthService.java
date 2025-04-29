package com.example.CloudBalanceBackend.Service;

import com.example.CloudBalanceBackend.DTO.LoginDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> login(LoginDTO loginDTO);
    ResponseEntity<?> logout(String authHeader);
}
