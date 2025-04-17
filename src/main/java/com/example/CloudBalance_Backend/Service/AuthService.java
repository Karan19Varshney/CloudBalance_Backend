package com.example.CloudBalance_Backend.Service;

import com.example.CloudBalance_Backend.DTO.LoginDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> login(LoginDTO loginDTO);
    ResponseEntity<?> logout(String authHeader);
}
