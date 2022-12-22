package com.example.firstproject.repository;

import com.example.firstproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByUsername(String username);
    long countBy();
    void deleteByEmail(String email);
}

