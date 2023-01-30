package com.cotato.study.SpringnAWS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableJpaAuditing이 삭제됨
@SpringBootApplication // 스프링 부트의 자동 설정, Spring 빈의 읽기와 생성을 자동 설정
public class Application { // 프로젝트의 메인 클래스
    public static void main(String[] args) {
            SpringApplication.run(Application.class, args); // 내장 WAS 서버 실행
    }
}
