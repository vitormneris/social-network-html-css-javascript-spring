package com.socialnetwork.socialnetwork.services;

import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UploadImageService uploadImageService;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity insert(UserEntity userEntity, MultipartFile image) {
        userEntity.setUrlImage(uploadImageService.uploadImage(image));
        return userRepository.save(userEntity);
    }
}
