package com.uwindsor.joke.controller;

import com.uwindsor.joke.service.NumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RandomNumberController {
    private final NumberService numberService;

    public RandomNumberController(NumberService numberService) {
        this.numberService = numberService;
    }


    @RequestMapping("/number")
    public String showNumber(Model model){

        System.out.println("hey, the number is here " + numberService.getNumber());

        model.addAttribute("number",numberService.getNumber());
        return "number";
    }



}
