package com.example.firstproject.controller;

import com.example.firstproject.dto.CreateAcquiredBoard;
import com.example.firstproject.dto.AcquiredBoardResponse;
import com.example.firstproject.dto.UpdateAcquiredBoard;
import com.example.firstproject.service.AcquiredBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@Controller
@RequestMapping("/acquired-board")
public class AcquiredBoardController {

    @Autowired
    AcquiredBoardService acquiredBoardService;


    @PostMapping()
    public String saveAcquiredBoard(CreateAcquiredBoard dto) throws IOException {
        AcquiredBoardResponse acquiredBoardResponse = acquiredBoardService.save(dto);
        return "redirect:/acquired-board/" + acquiredBoardResponse.getId();
    }

    @GetMapping("/form")
    public String getForm() {
        return "acquired-form";
    }

    @GetMapping("/form/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("acquired", acquiredBoardService.getById(id));
        return "acquired-update";
    }

    @GetMapping("/{id}")
    public String getAcquiredBoardById(@PathVariable Long id, Model model) throws MalformedURLException {
        AcquiredBoardResponse acquiredBoardResponse = acquiredBoardService.getById(id);
        model.addAttribute("detail", acquiredBoardResponse);
        return "acquired-detail";
    }

    @GetMapping()
    public String getAcquiredBoardList(Model model) {
        model.addAttribute("acquired", acquiredBoardService.getAll());
        return "acquired-list";
    }

    @ResponseBody
    @GetMapping("/image/{storeName}")
    public Resource getImageByName(@PathVariable String storeName) throws MalformedURLException {
        return acquiredBoardService.getImage(storeName);
    }

    @PostMapping("/update")
    public String updateAcquiredBoard(UpdateAcquiredBoard dto) throws IOException {
        AcquiredBoardResponse acquiredBoardResponse = acquiredBoardService.update(dto);
        return "redirect:/acquired-board/" + acquiredBoardResponse.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteAcquiredBoardById(@PathVariable Long id) {

        acquiredBoardService.deleteById(id);

        return "redirect:/acquired-board";
    }

}
