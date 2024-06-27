package com.socialnetwork.socialnetwork.config;

import com.socialnetwork.socialnetwork.infrastructure.entities.PublicationEntity;
import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.infrastructure.repositories.PublicationRepository;
import com.socialnetwork.socialnetwork.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    @Override
    public void run(String... args) throws Exception {
        UserEntity userEntity1 = new UserEntity(null, "João Vítor", "joao@gmail.com", "12345678", "/image/phots/joao.jpg", null);
        UserEntity userEntity2 = new UserEntity(null, "Maria Silva", "maria@gmail.com", "87654321", "/image/phots/maria.jpg", null);
        UserEntity userEntity3 = new UserEntity(null, "Carlos Pereira", "carlos@gmail.com", "12348765", "/image/phots/carlos.jpg", null);

        userRepository.saveAll(Arrays.asList(userEntity1, userEntity2, userEntity3));

        PublicationEntity publicationEntity1 = new PublicationEntity(null, "Bom dia!!", LocalDateTime.now().plusHours(-13), userEntity1);
        PublicationEntity publicationEntity2 = new PublicationEntity(null, "Boa tarde!!", LocalDateTime.now().plusDays(-6), userEntity2);
        PublicationEntity publicationEntity3 = new PublicationEntity(null, "Boa noite!!", LocalDateTime.now().plusMinutes(-15), userEntity3);

        publicationRepository.saveAll(Arrays.asList(publicationEntity1, publicationEntity2, publicationEntity3));
    }
}
