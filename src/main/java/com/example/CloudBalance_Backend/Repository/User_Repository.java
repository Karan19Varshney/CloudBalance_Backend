package com.example.CloudBalance_Backend.Repository;

import com.example.CloudBalance_Backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface User_Repository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
