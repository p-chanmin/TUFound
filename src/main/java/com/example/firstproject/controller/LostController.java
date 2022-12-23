package com.example.firstproject.controller;

import com.example.firstproject.dto.LostForm;
import com.example.firstproject.entity.Lost;
import com.example.firstproject.service.LostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@Slf4j //로깅
public class LostController {

    @Autowired //서비스 객체 = 실제 로직이 동작
    private LostService lostService;

    @GetMapping("/lost/new")
    public String newArticleForm(){

        return "lostGaesipan";
    }


    @PostMapping("/lost/create") //새 페이지
    public String creates(LostForm lost, @RequestParam("file") MultipartFile file)throws Exception{

        Lost saved= lostService.creates(lost,file);

        return "redirect:/lost/"+saved.getId();
    }

    //    상세조회
    @GetMapping("/lost/{id}")
    public String getLostBoardById(@PathVariable Long id, Model model) {
        lostService.getById(id,model);

        return "show";
    }

    @GetMapping("/lost")
    public String getLostBoardList(Model model) {
        lostService.show(model);
        return "mainPage";
    }

    @GetMapping("/lost/{id}/edit")
    public String edit(@PathVariable Long id,Model model){

        lostService.edit(id,model);
        //뷰 페이지 설정
        return"lostEdit";
    }



    @PostMapping("/lost/{id}/update") //수정페이지
    public String update(@PathVariable Long id,LostForm lost,@RequestParam("file") MultipartFile file)throws Exception{

        Lost update=lostService.update(id,lost,file);
        //뷰 페이지 설정
        return "redirect:/lost/"+update.getId();
    }


    @GetMapping("/lost/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){

        lostService.delete(id,rttr);
        return "redirect:/lost";
    }
}
