package com.example.firstproject.repository;

import com.example.firstproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
    Optional<MemberEntity> findByEmail(String userEmail);
}
