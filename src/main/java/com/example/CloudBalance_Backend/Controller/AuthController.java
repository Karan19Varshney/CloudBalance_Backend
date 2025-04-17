package com.example.CloudBalance_Backend.Controller;

import com.example.CloudBalance_Backend.DTO.LoginDTO;
import com.example.CloudBalance_Backend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'READ_ONLY', 'CUSTOMER')")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        return authService.logout(authHeader);
    }
}
