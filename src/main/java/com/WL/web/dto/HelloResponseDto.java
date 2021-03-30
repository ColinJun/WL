package com.WL.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
//@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;

    @Builder
    public HelloResponseDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
