package com.socialnetwork.socialnetwork.services;

import com.socialnetwork.socialnetwork.infrastructure.entities.PublicationEntity;
import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.infrastructure.repositories.PublicationRepository;
import com.socialnetwork.socialnetwork.infrastructure.repositories.UserRepository;
import com.socialnetwork.socialnetwork.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    public List<PublicationEntity> findAll() {
        return publicationRepository.findAll();
    }

    public PublicationEntity insert(PublicationEntity entity, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found."));
        entity.setDate(LocalDateTime.now());
        entity.setAuthor(user);
        return publicationRepository.save(entity);
    }
}
