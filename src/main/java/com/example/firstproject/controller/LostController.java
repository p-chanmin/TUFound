package com.example.firstproject.controller;

import com.example.firstproject.dto.LostForm;
import com.example.firstproject.entity.Lost;
import com.example.firstproject.service.LostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
@Slf4j //로깅
public class LostController {

    @Autowired //서비스 객체 = 실제 로직이 동작
    private LostService lostService;



    @PostMapping("/lost/create") //새 페이지
    public ResponseEntity<Lost> creates(LostForm lost, @RequestParam("file") MultipartFile file)throws Exception{

        Lost saved= lostService.creates(lost,file);


        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping()
    public ResponseEntity<List<Lost>> getAcquiredBoardList() {
        return ResponseEntity.ok(lostService.show());
    }
    
    //상세조회
    @GetMapping("lost/{id}")
    public ResponseEntity<Lost> getAcquiredBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(lostService.getById(id));
    }



    @PutMapping("/lost/{id}/edit") //수정페이지
    public ResponseEntity<Lost> edit(@PathVariable Long id,LostForm lost,@RequestParam("file") MultipartFile file)throws Exception{

        //뷰 페이지 설정
        return ResponseEntity.ok(lostService.update(id,lost,file));
    }


    @DeleteMapping("/lost/{id}")
    public void delete(@PathVariable Long id, RedirectAttributes rttr){

        lostService.delete(id,rttr);
    }
}
