package com.example.CloudBalance_Backend.Controller;

import com.example.CloudBalance_Backend.DTO.User_DTO;
import com.example.CloudBalance_Backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")

public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-user")
    public ResponseEntity<User_DTO> createUser(@RequestBody User_DTO userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'READ_ONLY')")
    @GetMapping("/users")
    public ResponseEntity<List<User_DTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-user/{id}")
    public ResponseEntity<User_DTO> updateUser(@PathVariable Long id, @RequestBody User_DTO userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-user/{id}")
    public ResponseEntity<User_DTO> getUserById(@PathVariable Long id) {
        User_DTO userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

}
