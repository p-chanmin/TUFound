package com.example.firstproject.repository;

import com.example.firstproject.entity.Member;
import com.example.firstproject.entity.Message;
import com.example.firstproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiver(Member user);
    List<Message> findAllBySender(Member user);
}