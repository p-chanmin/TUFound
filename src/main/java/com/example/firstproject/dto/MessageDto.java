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
    private String text;
    private Long otherId;
    private String otherName;

    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getContent(),
                message.getDate(),
                message.getSender().getUsername(),
                message.getReceiver().getUsername(),
                message.getSender().getId(),
                message.getReceiver().getId(),
                "",
                null,
                null
        );
    }
}