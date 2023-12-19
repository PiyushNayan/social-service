package com.example.product_service.controller;

import com.example.product_service.dto.UserDto;
import com.example.product_service.entity.User;
import com.example.product_service.exceptionHandler.ApiResponse;
import com.example.product_service.repository.UserRepository;
import com.example.product_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            boolean isUserAdded = userService.addUser(user);

            if (isUserAdded) {
                return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add user", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        try {
            Optional<User> user= userRepository.findById(userId);

            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/{userId}/friends")
//    public ResponseEntity<?> getListOfFriends(@PathVariable String userId) {
//        try {
//            Optional<User> optionalUser = userRepository.findById(userId);
//
//            if (optionalUser.isPresent() && optionalUser.get().getFriendList() != null) {
//                List<String> friends = optionalUser.get().getFriendList();
//                return new ResponseEntity<>(friends, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("User not found or has no friends", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/friends")
//    public List<String> getListOfFriends(@RequestParam String userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//
//        if (optionalUser.isPresent() && optionalUser.get().getFriendList() != null) {
//            List<User> friends = optionalUser.get().getFriendList();
//            return friends.stream()
//                    .map(User::getUserId)
//                    .collect(Collectors.toList());
//        } else {
//            return Collections.emptyList();
//        }
//    }

    @GetMapping("/users")
    public ApiResponse<List<User>> getAllUsers() {
        ApiResponse<List<User>> apiResponse;
        try {
            List<User> users = userService.getAllUsers();
            apiResponse = new ApiResponse<>(users);

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "No users found");
        }


        return apiResponse;
    }



}
