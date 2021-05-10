package com.WL.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor
public class MatchDetailDto {
    private Long gameId;
    private String gameType;
    private List teams;
    private List participants;

/*    public static class Teams{
        private int teamId; //String
        private String win;
    }
    public static class Participants{
        private int teamId;
        private int championId;
    }*/
}