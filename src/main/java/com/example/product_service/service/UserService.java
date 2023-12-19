package com.example.product_service.service;

import com.example.product_service.dto.UserDto;
import com.example.product_service.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Boolean addUser(User userDto);
    Optional<User> getUserById(String userId);
    List<String> getListOfFriends(String userId);
    List<User> getAllUsers();
}
