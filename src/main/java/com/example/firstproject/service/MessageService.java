package com.example.firstproject.service;

import com.example.firstproject.dto.MessageDto;
import com.example.firstproject.entity.Message;
import com.example.firstproject.entity.User;
import com.example.firstproject.repository.MessageRepository;
import com.example.firstproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional
    public MessageDto write(MessageDto messageDto) {
        User receiver = userRepository.findByName(messageDto.getReceiverName());
        User sender = userRepository.findByName(messageDto.getSenderName());

        Message message = new Message();
        message.setReceiver(receiver);
        message.setSender(sender);
        message.setDate(LocalDateTime.now());

        message.setContent(messageDto.getContent());
        message.setDeletedByReceiver(false);
        message.setDeletedBySender(false);
        messageRepository.save(message);

        return MessageDto.toDto(message);
    }

    @Transactional(readOnly = true)
    public List<MessageDto> allMessage(User user) {
        // 전체 편지함 불러오기
        // 한 명의 유저가 주고 받은 메세지의 최신 1개만 추출
        List<MessageDto> messageDtos = new ArrayList<>();

        List<Long> allId = new ArrayList<>();

        List<MessageDto> received = receivedMessage(user);
        List<MessageDto> sent = sentMessage(user);

        allId.addAll(
                sent.stream().map(m -> m.getReceiverId())
                        .distinct()
                        .collect(Collectors.toList()));
        allId.addAll(
                received.stream().map(m -> m.getSenderId())
                        .distinct()
                        .collect(Collectors.toList()));
        allId = allId.stream().distinct().collect(Collectors.toList());

        messageDtos.addAll(received);
        messageDtos.addAll(sent);

        List<MessageDto> messageRooms = allId.stream().map(i ->
                messageDtos.stream()
                        .filter(m -> m.getReceiverId() == i || m.getSenderId() == i)
                        .collect(Collectors.toList())
        ).map(l -> l.stream()
                        .sorted(Comparator.comparing(MessageDto::getDate).reversed())
                        .collect(Collectors.toList()).get(0)
                ).collect(Collectors.toList());

        return messageRooms;
    }
    @Transactional(readOnly = true)
    public List<MessageDto> getMessageRoom(User user, Long otherId) {
        // otherId 유저와 주고받은 편지함 불러오기
        List<MessageDto> messageDtos = new ArrayList<>();

        List<MessageDto> received = receivedMessage(user);
        List<MessageDto> sent = sentMessage(user);

        messageDtos.addAll(received);
        messageDtos.addAll(sent);

        List<MessageDto> messageRoom = messageDtos.stream()
                .filter(m -> m.getReceiverId() == otherId || m.getSenderId() == otherId)
                .sorted(Comparator.comparing(MessageDto::getDate).reversed())
                .collect(Collectors.toList());

        return messageRoom;
    }

    @Transactional(readOnly = true)
    public List<MessageDto> receivedMessage(User user) {
        // 받은 편지함 불러오기
        // 한 명의 유저가 받은 모든 메시지
        List<Message> messages = messageRepository.findAllByReceiver(user);
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            // message 에서 받은 편지함에서 삭제하지 않았으면 보낼 때 추가해서 보내줌
            if(!message.isDeletedByReceiver()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }

    // 받은 편지 삭제
    @Transactional
    public Object deleteMessageByReceiver(Long id, User user) {
        Message message = messageRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("메시지를 찾을 수 없습니다.");
        });

        if(user == message.getReceiver()) {
            message.deleteByReceiver(); // 받은 사람에게 메시지 삭제
            if (message.isDeleted()) {
                // 받은사람과 보낸 사람 모두 삭제했으면, 데이터베이스에서 삭제요청
                messageRepository.delete(message);
                return "양쪽 모두 삭제";
            }
            return "한쪽만 삭제";
        } else {
            return new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
    }



    @Transactional(readOnly = true)
    public List<MessageDto> sentMessage(User user) {
        // 보낸 편지함 불러오기
        // 한 명의 유저가 받은 모든 메시지
        List<Message> messages = messageRepository.findAllBySender(user);
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            // message 에서 받은 편지함에서 삭제하지 않았으면 보낼 때 추가해서 보내줌
            if(!message.isDeletedBySender()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }


    // 보낸 편지 삭제
    @Transactional
    public Object deleteMessageBySender(Long id, User user) {
        Message message = messageRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("메시지를 찾을 수 없습니다.");
        });

        if(user == message.getSender()) {
            message.deleteBySender(); // 받은 사람에게 메시지 삭제
            if (message.isDeleted()) {
                // 받은사람과 보낸 사람 모두 삭제했으면, 데이터베이스에서 삭제요청
                messageRepository.delete(message);
                return "양쪽 모두 삭제";
            }
            return "한쪽만 삭제";
        } else {
            return new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }


    }
}