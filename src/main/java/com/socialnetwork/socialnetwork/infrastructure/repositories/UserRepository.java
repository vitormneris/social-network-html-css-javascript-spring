package com.socialnetwork.socialnetwork.infrastructure.repositories;

import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
