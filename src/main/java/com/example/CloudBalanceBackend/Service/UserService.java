package com.example.CloudBalanceBackend.Service;

import com.example.CloudBalanceBackend.DTO.User_DTO;
import java.util.List;

public interface UserService {
    User_DTO createUser(User_DTO userDto);
    List<User_DTO> getAllUsers();
    User_DTO updateUser(Long id, User_DTO updatedUserDto);
    User_DTO getUserById(Long id);

}
