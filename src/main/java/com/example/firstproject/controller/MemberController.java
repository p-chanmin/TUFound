package com.example.firstproject.controller;

import com.example.firstproject.dto.AcquiredBoardResponse;
import com.example.firstproject.service.AcquiredBoardService;
import com.example.firstproject.service.MemberService;
import com.example.firstproject.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;
    private AcquiredBoardService acquiredBoardService;

    // 메인 페이지
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("acquired", acquiredBoardService.getAll());
        return "acquired-list";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "/signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        memberDto.toString();
        memberService.joinUser(memberDto);

        return "redirect:/acquired-board";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }


}
