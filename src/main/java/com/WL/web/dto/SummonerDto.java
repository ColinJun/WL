package com.WL.web.dto;

import lombok.*;

import java.util.Date;


@Getter
@NoArgsConstructor
public class SummonerDto {
        private String id;
        private String accountId;
        private String puuid;
        private String name;
        private String profileIconId;
        private String revisionDate;
        private String summonerLevel;
}