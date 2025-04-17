package com.example.CloudBalance_Backend.DTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class User_DTO {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String name;
    private LocalDateTime LastLogin;
    private Set<Long> AccountIds;

}

