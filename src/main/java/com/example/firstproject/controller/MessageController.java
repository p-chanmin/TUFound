package com.example.firstproject.controller;

import com.example.firstproject.service.MessageService;
import com.example.firstproject.dto.MessageDto;
import com.example.firstproject.entity.Message;
import com.example.firstproject.entity.User;
import com.example.firstproject.repository.MessageRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.response.Response;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    Long loginUserId = 2L; // 임의의 유저 정보, 로그인 기능 구현 후 현재 로그인 된 유저의 정보를 넘겨줘야함
    @ApiOperation(value = "전체 편지함 읽기", notes = "전체 편지함 확인")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages")
    public Response<?> getMessageList() {
        User user = userRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response("성공", "모든 대화방을 불러왔습니다.", messageService.allMessage(user));
    }
    @ApiOperation(value = "채팅방 읽기", notes = "해당 유저와 전체 쪽지 내용 확인")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/{id}")
    public Response<?> getMessageRoom(@PathVariable("id") Long id) {
        User user = userRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response("성공", id + "번 유저와의 대화방을 불러왔습니다.", messageService.getMessageRoom(user, id));
    }

    // content, receiverName 입력 필요
    // loginUserId는 로그인 상태에서 받아옴
    @ApiOperation(value = "쪽지 보내기", notes = "쪽지 보내기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/messages/new")
    public Response<?> sendMessage(@RequestBody MessageDto messageDto) {
        User user = userRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });
        messageDto.setSenderName(user.getName());

        return new Response<>("성공", "쪽지를 보냈습니다.", messageService.write(messageDto));
    }
    @ApiOperation(value = "쪽지 삭제하기", notes = "쪽지를 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/messages/delete/{id}")
    public Response<?> deleteMessage(@PathVariable("id") Long id) {
        User user = userRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });
        Message message = messageRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("메세지를 찾을 수 없습니다.");
        });
        if(message.getSender().getId() == loginUserId){
            return deleteSentMessage(id);
        }
        else{
            return deleteReceivedMessage(id);
        }
    }

    @ApiOperation(value = "받은 편지함 읽기", notes = "받은 편지함 확인")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> getReceivedMessage() {
        // 임의로 유저 정보를 넣었지만, JWT 도입하고 현재 로그인 된 유저의 정보를 넘겨줘야함
        User user = userRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response("성공", "받은 쪽지를 불러왔습니다.", messageService.receivedMessage(user));
    }

    @ApiOperation(value = "보낸 편지함 읽기", notes = "보낸 편지함 확인")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> getSentMessage() {
        // 임의로 유저 정보를 넣었지만, JWT 도입하고 현재 로그인 된 유저의 정보를 넘겨줘야함
        User user = userRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response("성공", "보낸 쪽지를 불러왔습니다.", messageService.sentMessage(user));
    }

    @ApiOperation(value = "받은 쪽지 삭제하기", notes = "받은 쪽지를 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> deleteReceivedMessage(@PathVariable("id") Long id) {
        User user = userRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response<>("삭제 성공", "받은 쪽지인, " + id + "번 쪽지를 삭제했습니다.", messageService.deleteMessageByReceiver(id, user));
    }

    @ApiOperation(value = "보낸 쪽지 삭제하기", notes = "보낸 쪽지를 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> deleteSentMessage(@PathVariable("id") Long id) {
        User user = userRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response<>("삭제 성공", "보낸 쪽지인, " + id + "번 쪽지를 삭제했습니다.", messageService.deleteMessageBySender(id, user));
    }


}

