package com.example.CloudBalance_Backend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "USER_DETAILS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Email
    @NotNull
    private String email;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private ERole role;

    @Column(name = "Last_Login")
    private LocalDateTime Last_Login;


    @ManyToMany
    @JoinTable(
            name = "user_accounts_mapping",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id")
    )
    private Set<Account> accounts = new HashSet<>();
}
