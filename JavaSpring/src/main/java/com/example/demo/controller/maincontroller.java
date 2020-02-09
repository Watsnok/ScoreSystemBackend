package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class maincontroller {

    @RequestMapping("/")
    public String showMain(Model model){
        model.addAttribute("title", "Hello world");
        //model.addAttribute("listofpeople", )
        return "index";
    }

}
