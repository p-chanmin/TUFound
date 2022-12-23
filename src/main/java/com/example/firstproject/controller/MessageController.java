package com.example.firstproject.controller;

import com.example.firstproject.dto.MessageDto;
import com.example.firstproject.entity.Member;
import com.example.firstproject.entity.Message;
import com.example.firstproject.entity.User;
import com.example.firstproject.repository.MemberRepository;
import com.example.firstproject.repository.MessageRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.response.Response;
import com.example.firstproject.service.MessageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MessageController {

    private final MessageService messageService;
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;


    Long loginUserId = 2L; // 임의의 유저 정보, 로그인 기능 구현 후 현재 로그인 된 유저의 정보를 넘겨줘야함
    @ApiOperation(value = "전체 편지함 읽기", notes = "전체 편지함 확인")
    @GetMapping("/messages")
    public String getMessageList(Model model) {
        Member user = memberRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        model.addAttribute("messages",
                messageService.allMessage(user).stream().map(m -> {
                    if(m.getSenderId() == user.getId()){
                        m.setText("보낸쪽지");
                        m.setOtherId(m.getReceiverId());
                        m.setOtherName(m.getReceiverName());
                    }
                    else{
                        m.setText("받은쪽지");
                        m.setOtherId(m.getSenderId());
                        m.setOtherName(m.getSenderName());
                    }
                    return m;
                }).collect(Collectors.toList())
        );
        return "keepMessage";
    }
    @ApiOperation(value = "채팅방 읽기", notes = "해당 유저와 전체 쪽지 내용 확인")
    @GetMapping("/messages/{id}")
    public String getMessageRoom(@PathVariable("id") Long id, Model model) {
        Member user = memberRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });
        Member who = memberRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });
        model.addAttribute("who", who.getUsername());
        model.addAttribute("messages",
                messageService.getMessageRoom(user, id).stream().map(m -> {
                        if(m.getSenderId() == user.getId()){
                            m.setText("보낸쪽지");
                            m.setOtherId(m.getReceiverId());
                            m.setOtherName(m.getReceiverName());
                        }
                        else{
                            m.setText("받은쪽지");
                            m.setOtherId(m.getSenderId());
                            m.setOtherName(m.getSenderName());
                        }
                        return m;
                    }).collect(Collectors.toList())
                );

        return "keepMessage-detail";
    }

    @GetMapping("/messages/post")
    private String newMessageForm(@ModelAttribute MessageDto messageDto) {
        return "postMessage";
    }

    // content, receiverName 입력 필요
    // loginUserId는 로그인 상태에서 받아옴
    @ApiOperation(value = "쪽지 보내기", notes = "쪽지 보내기")
    @PostMapping("/messages/new")
    public String sendMessage(@ModelAttribute MessageDto messageDto) {
        Member user = memberRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });
        Member to = memberRepository.findByUsername(messageDto.getReceiverName()).get();
        messageDto.setSenderName(user.getUsername());
        messageDto.setText("");
        messageDto.setOtherId(0L);
        messageDto.setOtherName("");
        messageService.write(messageDto);

        return "redirect:/messages/"+to.getId();
    }
    @ApiOperation(value = "쪽지 삭제하기", notes = "쪽지를 삭제합니다.")
    @GetMapping("/messages/delete/{id}")
    public String deleteMessage(@PathVariable("id") Long id) {
        Member user = memberRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });
        Message message = messageRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("메세지를 찾을 수 없습니다.");
        });
        Long who;
        if(message.getSender().getId() == loginUserId){
            deleteSentMessage(id);
            who = message.getReceiver().getId();
        }
        else{
            deleteReceivedMessage(id);
            who = message.getSender().getId();
        }
        return "redirect:/messages/"+who;
    }

    @ApiOperation(value = "받은 편지함 읽기", notes = "받은 편지함 확인")
    public Response<?> getReceivedMessage() {
        // 임의로 유저 정보를 넣었지만, JWT 도입하고 현재 로그인 된 유저의 정보를 넘겨줘야함
        Member user = memberRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response("성공", "받은 쪽지를 불러왔습니다.", messageService.receivedMessage(user));
    }

    @ApiOperation(value = "보낸 편지함 읽기", notes = "보낸 편지함 확인")
    public Response<?> getSentMessage() {
        // 임의로 유저 정보를 넣었지만, JWT 도입하고 현재 로그인 된 유저의 정보를 넘겨줘야함
        Member user = memberRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response("성공", "보낸 쪽지를 불러왔습니다.", messageService.sentMessage(user));
    }

    @ApiOperation(value = "받은 쪽지 삭제하기", notes = "받은 쪽지를 삭제합니다.")
    public Response<?> deleteReceivedMessage(@PathVariable("id") Long id) {
        Member user = memberRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response<>("삭제 성공", "받은 쪽지인, " + id + "번 쪽지를 삭제했습니다.", messageService.deleteMessageByReceiver(id, user));
    }

    @ApiOperation(value = "보낸 쪽지 삭제하기", notes = "보낸 쪽지를 삭제합니다.")
    public Response<?> deleteSentMessage(@PathVariable("id") Long id) {
        Member user = memberRepository.findById(loginUserId).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return new Response<>("삭제 성공", "보낸 쪽지인, " + id + "번 쪽지를 삭제했습니다.", messageService.deleteMessageBySender(id, user));
    }


}

