package com.cotato.study.SpringnAWS.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class) // 테스트 진행 시 JUnit에 내장된 실행자 외에 SpringRunner라는 스프링 실행자를 사용, 즉 스프링 부트 테스트와 JUnit 사이의 연결자
@SpringBootTest // 별다른 설정 없이 사용할 경우 H2 데이터베이스를 자동으로 실행
public class PostsRepositoryTest{
    @Autowired
    PostsRepository postsRepository;

    @After // JUnit에서 단위 테스트가 종료 될 때마다 수행되는 메서드
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // 테이블 posts에 Insert와 Update 쿼리를 실행
                .title(title)
                .content(content)
                .author("cotato@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); // posts에 있는 모든 데이터를 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2023,01,18,0,0,0);
        postsRepository.save(Posts.builder().
                title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> CreatedDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}