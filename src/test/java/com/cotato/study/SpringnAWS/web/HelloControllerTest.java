package com.cotato.study.SpringnAWS.web;

import com.cotato.study.SpringnAWS.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 테스트 진행 시 JUnit에 내장된 실행자 외에 SpringRunner라는 스프링 실행자를 사용, 즉 스프링 부트 테스트와 JUnit 사이의 연결자
@WebMvcTest(controllers = HelloController.class, // 많은 테스트 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션
    excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                    classes = SecurityConfig.class)
    }
)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입 받음
    private MockMvc mvc; // 웹 API 테스트를 위함 (GET, POST 등)

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello")) // MockMVC를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // 앞선 코드의 결과의 HTTP 헤더의 상태가 OK(200)인지 확인
                .andExpect(content().string((hello))); // 응답 본문의 결과가 변수 hello의 값과 일치하는지 확인
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String
                                .valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
