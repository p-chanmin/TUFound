package com.example.firstproject.controller;

import com.example.firstproject.service.AcquiredBoardService;
import com.example.firstproject.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AcquiredBoardService acquiredBoardService;
    @Autowired
    AdminService adminService;

    @GetMapping()
    public String getDashboard(Model model) {
        model.addAttribute("ac", acquiredBoardService.getCount());
        model.addAttribute("mem", adminService.countAllMember());
        model.addAttribute("lost", adminService.countAllLost());
        return "admin";
    }

    @GetMapping("/user")
    public String getUserBoard(Model model) {
        model.addAttribute("mems", adminService.getMemberAll());
        return "admin-user";
    }

    @PostMapping("/user/{email}")
    public String deleteUser(@PathVariable String email) {
        adminService.deleteUser(email);
        return "redirect:/admin/user";
    }


}
