package com.example.firstproject.dto;

import com.example.firstproject.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;
    private String content;
    private LocalDateTime date;
    private String senderName;
    private String receiverName;
    private Long senderId;
    private Long receiverId;

    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getContent(),
                message.getDate(),
                message.getSender().getName(),
                message.getReceiver().getName(),
                message.getSender().getId(),
                message.getReceiver().getId()
        );
    }
}