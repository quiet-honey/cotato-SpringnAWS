package com.cotato.study.SpringnAWS.web;

import com.cotato.study.SpringnAWS.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환하도록 만들어줌
public class HelloController {
    @GetMapping("/hello") // HTTP Method인 Get 요청을 받을 수 있는 API 생성
    public String hello(){
        return "hello"; // 화면에 hello를 띄움
    }

    @GetMapping("/hello/dto") // HTTP Method인 Get 요청을 받을 수 있는 API 생성
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount); // name, amount를 가지는 HelloResponseDto를 반환
    }
}
