package com.example.CloudBalanceBackend.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Accounts_Data")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "account_id",nullable = false)
    private Long accountId;

    @Column(unique = true, nullable = false)
    private String arn;

}
