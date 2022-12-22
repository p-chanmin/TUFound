package com.example.firstproject.repository;

<<<<<<< HEAD
import com.example.firstproject.entity.MemberEntity;
=======
import com.example.firstproject.entity.Member;
>>>>>>> 01668cf75e47e050db4822f9f3dbc1c2d99a0332
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

<<<<<<< HEAD
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
    Optional<MemberEntity> findByEmail(String userEmail);
    long countBy();
    void deleteByEmail(String email);
}
=======
public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByUsername(String username);
}
>>>>>>> 01668cf75e47e050db4822f9f3dbc1c2d99a0332
