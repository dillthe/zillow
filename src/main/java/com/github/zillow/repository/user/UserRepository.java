package com.github.zillow.repository.user;

import com.github.zillow.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

//    Optional<UserEntity> findUserEntityById(Integer userId);
    Optional<UserEntity> findByName(String name);
}
