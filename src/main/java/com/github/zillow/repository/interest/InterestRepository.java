package com.github.zillow.repository.interest;

import com.github.zillow.repository.entity.InterestEntity;
import com.github.zillow.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Integer> {

    List<InterestEntity> findByUserEntity(UserEntity userEntity);
}
