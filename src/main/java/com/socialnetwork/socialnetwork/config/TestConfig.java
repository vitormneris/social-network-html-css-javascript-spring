package com.socialnetwork.socialnetwork.config;

import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        UserEntity userEntity1 = new UserEntity(null, "João Vítor", "joao@gmail.com", "12345678", "/image/phots/joao.jpg");
        UserEntity userEntity2 = new UserEntity(null, "Maria Silva", "maria@gmail.com", "87654321", "/image/phots/maria.jpg");
        UserEntity userEntity3 = new UserEntity(null, "Carlos Pereira", "carlos@gmail.com", "12348765", "/image/phots/carlos.jpg");

        userRepository.saveAll(Arrays.asList(userEntity1, userEntity2, userEntity3));
    }
}
