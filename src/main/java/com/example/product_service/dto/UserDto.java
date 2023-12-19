package com.example.product_service.dto;


import com.example.product_service.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    String userId;
    String userName;
    private List<User> friendList;
    String url;
}
