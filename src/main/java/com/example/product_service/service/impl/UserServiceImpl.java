package com.example.product_service.service.impl;

import com.example.product_service.dto.UserDto;
import com.example.product_service.entity.User;
import com.example.product_service.repository.UserRepository;
import com.example.product_service.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean addUser(User userDto){
        try {
            if (userDto != null && userDto.getUserName()!= null && !userDto.getUserName().isEmpty() ) {
                User user = new User();
                BeanUtils.copyProperties(userDto, user);
                if (user.getUserName() == null || user.getFriendList() == null) {
                    throw new IllegalArgumentException("User details cannot be null");
                }
//                String userId = UUID.randomUUID().toString();
//                user.setUserId(userId);
                userRepository.save(user);
                return true;
            } else {
                throw new IllegalArgumentException("UserDto cannot be null");
            }
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<User> getUserById(String userId){
        Optional <User> user=userRepository.findById(userId);
        return user;
    }

    @Override
    public List<String> getListOfFriends(String userId) {
        return null;
    }

//    @Override
//    public List<String> getListOfFriends(String userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent() && optionalUser.get().getFriendList() != null) {
//            List<User> friends = optionalUser.get().getFriendList();
//            return friends.stream()
//                    .map(User::getUserId)
//                    .collect(Collectors.toList());
//        } else {
//            return Collections.emptyList();
//        }
//    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

}


