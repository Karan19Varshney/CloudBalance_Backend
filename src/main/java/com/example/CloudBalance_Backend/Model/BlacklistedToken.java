package com.example.CloudBalance_Backend.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Data
@Entity
@Table(name = "blacklisted_tokens")
public class BlacklistedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Date expiryDate;

}