package com.wb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("common")
public class CommonController {


    @RequestMapping("index")
    public String index() {
        return "main";
    }

    @RequestMapping("essay")
    public String essay() {
        return "redirect:/essay/index/1";
    }

    @RequestMapping("leaveWord")
    public String leaveWord() {
        return "redirect:/leaveWord/index/1";
    }

    @RequestMapping("album")
    public String album() {
        return "redirect:/album/index/1";
    }

    @RequestMapping("about")
    public String about() {
        return "about";
    }

    @RequestMapping("setting")
    public String setting() {
        return "setting";
    }

    @RequestMapping("log")
    public String log(){
        return "log";
    }
}
