// IndexController.java
package com.cotato.study.SpringnAWS.web;

import com.cotato.study.SpringnAWS.config.auth.LoginUser;
import com.cotato.study.SpringnAWS.config.auth.dto.SessionUser;
import com.cotato.study.SpringnAWS.service.posts.PostsService;
import com.cotato.study.SpringnAWS.web.dto.PostsReponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    /*
    기존 방식 : 로그인 성공 시 HttpSession에서 값을 받아옴
    private final HttpSession httpSession;
     */

    @GetMapping("/")
    public String index(Model model,
                        @LoginUser SessionUser user) { // 이제 어느 컨트롤러에서든 @LoginUser만 사용하면 세션 정보를 가져올 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        /* 기존 방식 : 로그인 성공 시 httpSession.getAttribute("user") 에서 값 가져옴
        SessionUser user = (SessionUser) httpSession.getAttribute("user"); */

        if (user != null) {	// session에 저장된 값이 있을 때만 model에 userName으로 등록
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsReponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
