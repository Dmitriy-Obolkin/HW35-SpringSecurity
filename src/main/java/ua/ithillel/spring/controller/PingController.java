package ua.ithillel.spring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    String ping(){
        return "OK";
    }
}
