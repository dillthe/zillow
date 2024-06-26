package com.github.zillow.web.controller;

import com.github.zillow.service.AuthService;
import com.github.zillow.web.dto.auth.Login;
import com.github.zillow.web.dto.auth.SignUp;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController implements ApiController {
    private final AuthService authService;

    @PostMapping("/sign/register")
    public String register(@RequestBody SignUp signUpRequest,  HttpServletResponse httpServletResponse){
        boolean isSuccess= authService.signUp(signUpRequest);
//        return isSuccess? "회원가입 성공하셨습니다." : "회원가입 실패하였습니다.";
        if (isSuccess) {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED); // HTTP 201 Created
            return "회원가입 성공하셨습니다.";
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST); // HTTP 400 Bad Request
            return "회원가입 실패하였습니다.";
        }
    }

    @PostMapping("/sign/login")
    public String login(@RequestBody Login loginRequest, HttpServletResponse httpServletResponse){
        String token = authService.login(loginRequest);
        httpServletResponse.setHeader("X-AUTH-TOKEN",token);
        return "로그인이 성공하였습니다.";
         }
}
