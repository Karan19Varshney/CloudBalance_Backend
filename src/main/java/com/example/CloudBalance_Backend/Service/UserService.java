package com.example.CloudBalance_Backend.Service;

import com.example.CloudBalance_Backend.DTO.User_DTO;
import java.util.List;

public interface UserService {
    User_DTO createUser(User_DTO userDto);
    List<User_DTO> getAllUsers();
    User_DTO updateUser(Long id, User_DTO updatedUserDto);
    User_DTO getUserById(Long id);

}
