package com.cotato.study.SpringnAWS.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내의 모든 필드의 Getter 메서드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // Table과 링크될 클래스임을 나타내는 어노테이션
public class Posts extends BaseTimeEntity{ // 실제 DB과 매칭될 클래스(엔티티 클래스)
    @Id // 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, auto_increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
