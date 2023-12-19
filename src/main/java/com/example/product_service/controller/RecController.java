package com.example.product_service.controller;


import com.example.product_service.dto.RecommendationDto;
import com.example.product_service.dto.UserDto;
import com.example.product_service.entity.Recommendation;
import com.example.product_service.entity.User;
import com.example.product_service.repository.RecCustomRepository;
import com.example.product_service.repository.RecRepository;
import com.example.product_service.repository.UserRepository;
import com.example.product_service.service.RecService;
import com.example.product_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/recommendations")
public class RecController {

    @Autowired
    private RecCustomRepository recCustomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecService recService;

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResponseEntity<String> addEntry(@RequestBody RecommendationDto recommendationDto) {
        try {
            boolean isEntryAdded = recService.addEntry(recommendationDto);

            if (isEntryAdded) {
                return new ResponseEntity<>("Recommendation entry added successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add recommendation entry", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{currentUserId}/friends/bought/{productId}")
    public ResponseEntity<?> getFriendsWhoBoughtProduct(@PathVariable String currentUserId, @PathVariable String productId) {
        try {
            List<Recommendation> recommendations = recCustomRepository.getRecommendationByProductId(productId);
            System.out.println(recommendations);
            List<UserDto>  myFriends = userRepository.findById(currentUserId).get().getFriendList();

            System.out.println(myFriends);

            List<UserDto> results = new ArrayList<>();



                for (UserDto friend:myFriends) {
                    for (Recommendation recommendation: recommendations) {
                        if (friend.getUserId().equals( recommendation.getUserId())) {
                            results.add(friend);
                        }
                    }
                }

//            List<String> friendsWhoBoughtProduct = recService.getFriendsWhoBoughtProduct(currentUserId, productId);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
