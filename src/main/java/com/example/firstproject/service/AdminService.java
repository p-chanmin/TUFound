package com.example.firstproject.service;

import com.example.firstproject.entity.MemberEntity;
import com.example.firstproject.repository.LostRepository;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LostRepository lostRepository;

    public long countAllMember() {
        return memberRepository.countBy();
    }

    public long countAllLost() {
        return lostRepository.countBy();
    }

    public List<MemberEntity> getMemberAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void deleteUser(String email) {
        memberRepository.deleteByEmail(email);
    }
}
