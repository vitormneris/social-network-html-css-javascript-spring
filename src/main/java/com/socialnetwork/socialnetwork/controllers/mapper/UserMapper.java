package com.socialnetwork.socialnetwork.controllers.mapper;

import com.socialnetwork.socialnetwork.controllers.dto.UserDTO;
import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity userDTOToUserEntity(UserDTO userDTO) {
        return UserEntity.builder()
            .username(userDTO.username())
            .email(userDTO.email())
            .password(userDTO.password())
            .build();
    }
}
