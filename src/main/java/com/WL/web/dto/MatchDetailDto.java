package com.WL.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class MatchDetailDto {
    private Long gameId;
    private List teams;
    private String gameType;
}