//SecurityConfig.java
package com.cotato.study.SpringnAWS.config.auth;

import com.cotato.study.SpringnAWS.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점, antMather 옵션을 사용하기 위한 필수 조건
                // antMatchers는 권한 관리 대상을 지정하는 옵션
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**","/profile") // "/", "/css" 등은
                .permitAll() // 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // "/api/v1/**" 주소를 가진 API는
                .anyRequest().authenticated() // USER 권한을 가진 사람만 가능
                .and()
                .logout() // 로그아웃 기능에 대한 여러 설정의 진입점
                .logoutSuccessUrl("/") // 로그아웃 성공 시에 "/" 주소로 이동
                .and()
                .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 시행할 UserService의 구현체를 등록
    }
}