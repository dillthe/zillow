//package com.github.zillow.web.controller;
//
//import com.github.zillow.service.AuthService;
//import com.github.zillow.web.dto.auth.SignUp;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class SignController implements ApiController {
//    private final AuthService authService;
//
//    @PostMapping("/sign/register")
//    public String register(@RequestBody SignUp signUpRequest){
//        boolean isSuccess= authService.signUp(signUpRequest);
//        return isSuccess? "회원가입 성공하셨습니다." : "회원가입 실패하였습니다.";
//    }
//}
