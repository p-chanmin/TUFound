package com.example.firstproject.repository;

import com.example.firstproject.entity.Message;
import com.example.firstproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiver(User user);
    List<Message> findAllBySender(User user);
}