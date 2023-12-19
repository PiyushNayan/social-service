package com.example.product_service.entity;


import com.example.product_service.dto.UserDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection="user")
public class User {

    @Id
    String userId;
    String userName;
    List<UserDto> friendList;
    String url;


}
