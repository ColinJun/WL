package com.WL.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        //mvc가 아니라서 MockMvc를 안쓰고 requiredArgsConstructor로 만든 생성자를 불러서 사용함

        //then
        assertThat(dto.getName()).isEqualTo(name); //getter에 의해 getName가능
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
