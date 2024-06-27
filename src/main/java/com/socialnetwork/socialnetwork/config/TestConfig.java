package com.socialnetwork.socialnetwork.config;

import com.socialnetwork.socialnetwork.infrastructure.entities.PublicationEntity;
import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.infrastructure.entities.enums.UserRole;
import com.socialnetwork.socialnetwork.infrastructure.repositories.PublicationRepository;
import com.socialnetwork.socialnetwork.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    @Override
    public void run(String... args) throws Exception {

        UserEntity adminEntity = new UserEntity(null, "admin", "admin@admin.com", new BCryptPasswordEncoder().encode("admin"), UserRole.ADMIN);
        UserEntity userEntity1 = new UserEntity(null, "João Vítor", "joao@gmail.com", new BCryptPasswordEncoder().encode("12345678"), "/image/phots/joao.jpg", UserRole.USER, new ArrayList<>());
        UserEntity userEntity2 = new UserEntity(null, "Maria Silva", "maria@gmail.com", new BCryptPasswordEncoder().encode("87654321"), "/image/phots/maria.jpg", UserRole.USER, new ArrayList<>());
        UserEntity userEntity3 = new UserEntity(null, "Carlos Pereira", "carlos@gmail.com", new BCryptPasswordEncoder().encode("12348765"), "/image/phots/carlos.jpg", UserRole.USER, new ArrayList<>());

        userRepository.saveAll(Arrays.asList(adminEntity, userEntity1, userEntity2, userEntity3));

        PublicationEntity publicationEntity1 = new PublicationEntity(null, "Bom dia!!", LocalDateTime.now().plusHours(-13), userEntity1);
        PublicationEntity publicationEntity2 = new PublicationEntity(null, "Boa tarde!!", LocalDateTime.now().plusDays(-6), userEntity2);
        PublicationEntity publicationEntity3 = new PublicationEntity(null, "Boa noite!!", LocalDateTime.now().plusMinutes(-15), userEntity3);

        publicationRepository.saveAll(Arrays.asList(publicationEntity1, publicationEntity2, publicationEntity3));

        userEntity1.getPublications().add(publicationEntity1);
        userEntity2.getPublications().add(publicationEntity2);
        userEntity3.getPublications().add(publicationEntity3);

        userRepository.saveAll(Arrays.asList(userEntity1, userEntity2, userEntity3));
    }
}
