package com.example.CloudBalance_Backend.Service.Implementation;

import com.example.CloudBalance_Backend.DTO.LoginDTO;
import com.example.CloudBalance_Backend.Model.BlacklistedToken;
import com.example.CloudBalance_Backend.Model.User;
import com.example.CloudBalance_Backend.Repository.BlacklistedTokenRepository;
import com.example.CloudBalance_Backend.Repository.User_Repository;
import com.example.CloudBalance_Backend.Security.Jwt.JwtUtils;
import com.example.CloudBalance_Backend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BlacklistedTokenRepository blacklistedTokenRepository;

    @Autowired
    private User_Repository userRepository;

    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtils.generateJwt(loginDTO.getEmail());

            User user = userRepository.findByEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            LocalDateTime now = LocalDateTime.now();
            user.setLast_Login(now);
            userRepository.save(user);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "name", user.getName(),
                    "role", user.getRole().name(),
                    "lastLogin", now.toString()
            ));

        } catch (BadCredentialsException ex) {
            return ResponseEntity

                    .status(401)
                    .body(Map.of("error", "Invalid Credentials"));
        }
    }

    @Override
    public ResponseEntity<?> logout(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Date expiryDate = jwtUtils.extractExpiration(token);

            BlacklistedToken blacklistedToken = new BlacklistedToken();
            blacklistedToken.setToken(token);
            blacklistedToken.setExpiryDate(expiryDate);

            blacklistedTokenRepository.save(blacklistedToken);
            SecurityContextHolder.clearContext();

            return ResponseEntity.ok(Map.of("message", "Logged out and token blacklisted"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid Authorization header"));
        }
    }
}