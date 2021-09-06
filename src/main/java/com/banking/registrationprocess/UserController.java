package com.banking.registrationprocess;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    //when useing spring security it auto navigate to the http://localhost:8010/login when we try any url
    //it s login interface we want to give user =user and password =(console eke print wena ek)
    @GetMapping("/home")
    public String home(){
        return "This is Home Page";
    }

    @GetMapping("/admin")
    public String admin(){
        return "This is Admin Page";
    }
}
