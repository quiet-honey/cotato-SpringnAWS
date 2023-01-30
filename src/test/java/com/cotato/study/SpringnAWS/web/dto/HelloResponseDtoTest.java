package com.cotato.study.SpringnAWS.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        // 검증할 대상을 assertThat의 인자로 받고, isEqualTo의 인자와 동일한지 체크
        assertThat(dto.getName()).isEqualTo(name); // dto의 name과 변수 name이 동일한지 테스트
        assertThat(dto.getAmount()).isEqualTo(amount); // dto의 amount과 변수 amount가 동일한지 테스트
    }

}
