package com.github.zillow.repository.member;

import com.github.zillow.repository.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
}
