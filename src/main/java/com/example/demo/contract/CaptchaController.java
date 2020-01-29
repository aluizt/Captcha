package com.example.demo.contract;

import com.example.demo.captcha.CaptchaValidator;
import com.example.demo.model.request.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CaptchaController {

    private final CaptchaValidator captchaValidator;

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest){
        if( captchaValidator.validateCaptcha(loginRequest.getCaptchaRequest())) return "login efetuado";
        return "login não efetuado";
    }
}
