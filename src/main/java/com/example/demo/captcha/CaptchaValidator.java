package com.example.demo.captcha;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CaptchaValidator {

    private static final String RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";
    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    public boolean validateCaptcha(String captchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        var apiResponse = restTemplate.postForObject(RECAPTCHA_ENDPOINT, getMap(captchaResponse), CaptchaResponse.class);
        if (apiResponse == null || !apiResponse.getSuccess()) {
            log.error("Não autorizado");
            return false;}
        log.info("Autorizado");
        return Boolean.TRUE.equals(apiResponse.getSuccess());
    }

    private MultiValueMap<String, String> getMap(String captchaResponse) {
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", recaptchaSecret);
        requestMap.add("response", captchaResponse);
        return requestMap;
    }
}