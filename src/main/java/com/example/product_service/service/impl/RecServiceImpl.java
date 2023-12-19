package com.example.product_service.service.impl;

import com.example.product_service.dto.RecommendationDto;
import com.example.product_service.entity.Recommendation;
import com.example.product_service.entity.User;
import com.example.product_service.repository.RecRepository;
import com.example.product_service.repository.UserRepository;
import com.example.product_service.service.RecService;
import com.example.product_service.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class RecServiceImpl implements RecService {
    @Autowired
    private RecRepository recRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public Boolean addEntry(RecommendationDto recommendationDto) {
        try {
            Recommendation recommendation = new Recommendation();
            recommendation.setIsPrivate(false);
            BeanUtils.copyProperties(recommendationDto, recommendation);
            recRepository.save(recommendation);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> getFriendsWhoBoughtProduct(String productId, String currentUserId) {
        User currentUser = userRepository.findById(currentUserId).orElse(null);

        if (currentUser != null) {
            List<String> friendIds = userService.getListOfFriends(currentUserId);
            List<String> userIdsFromRecommendations = recRepository.findUserIdsByProductIdAndIsPrivateFalse(productId);
            return userIdsFromRecommendations.stream()
                    .filter(friendIds::contains)
                    .collect(Collectors.toList());
        } else {

            return Collections.emptyList();
        }
    }

}

