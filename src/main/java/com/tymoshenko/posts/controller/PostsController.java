package com.tymoshenko.posts.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PostsController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin")
    public String admin(){
        return "get admin";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/logined_succesfully")
    public String loginedSuccesfully(){
        return "get logined_succesfully";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public String login3(){
        return "post without body";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/perform_login")
    public String login4(){
        return "/about";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public String login2(){
        return "get without body";
    }

}
