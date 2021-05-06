package com.WL.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class MatchDto {
        private List matches;
        private int startIndex;
        private int endIndex;
        private int totalGames;
}