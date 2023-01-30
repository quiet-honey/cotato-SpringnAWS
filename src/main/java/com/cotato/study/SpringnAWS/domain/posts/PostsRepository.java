// PostsRepository.java
package com.cotato.study.SpringnAWS.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepository를 상속하기만 해도 CRUD 메서드가 생성됨
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
