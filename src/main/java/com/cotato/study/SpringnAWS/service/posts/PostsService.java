//PostsService.java
package com.cotato.study.SpringnAWS.service.posts;

import com.cotato.study.SpringnAWS.domain.posts.Posts;
import com.cotato.study.SpringnAWS.domain.posts.PostsRepository;
import com.cotato.study.SpringnAWS.web.dto.PostsListReponseDto;
import com.cotato.study.SpringnAWS.web.dto.PostsReponseDto;
import com.cotato.study.SpringnAWS.web.dto.PostsSaveRequestDto;
import com.cotato.study.SpringnAWS.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsReponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsReponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListReponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()// postsRepository의 결과로 넘어온 Posts의 stream을
                .map(PostsListReponseDto::new) // map을 통해 PostsListResponseDto로 변환하고
                .collect(Collectors.toList()); // 이를 List로 반환하는 메소드
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts); // JpaRepository에서 제공하는 delete 메서드를 활용
    }
}