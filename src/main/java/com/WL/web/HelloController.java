package com.WL.web;

import com.WL.web.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

@ConfigurationProperties("key")
@RequiredArgsConstructor
@Controller
public class HelloController {
    @Autowired
    ConfigProperty configProperty;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/summoner/{id}", method=RequestMethod.GET)
    public String getPlayers(@PathVariable("id") final String id, Model model) throws Exception{

        Object object;
        ResponseMapper responseMapper = new ResponseMapper();
        String summonerName = id;//name.replaceAll(" ", "%20");
        String requestURL = "";
        String accountId = "";



        /* 소환사 정보 */
        requestURL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ summonerName + "?api_key=" + configProperty.getRiot();
        object = responseMapper.getRiotData(requestURL, SummonerDto.class);
        SummonerDto summoner = (SummonerDto) object;
        model.addAttribute("summoner", summoner);

        accountId = summoner.getAccountId(); //encryptedAccountId

        /* 매치 리스트 정보 */
        requestURL = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+ accountId + "?api_key=" + configProperty.getRiot();
        object = responseMapper.getRiotData(requestURL, MatchDto.class);
        MatchDto match =  (MatchDto) object;
        model.addAttribute("match", match);

        String gameId = "";
        for (int i = 0; i < match.getMatches().size(); i++){
            LinkedHashMap map = (LinkedHashMap) match.getMatches().get(i);
            System.out.println(map.get("champion"));
            System.out.println(map.get("gameId"));
            gameId = Long.toString((Long) map.get("gameId"));
            System.out.println(map.get("season"));
        }
        /* 매치 상세 정보 */

        requestURL = "https://kr.api.riotgames.com/lol/match/v4/matches/"+ gameId + "?api_key=" + configProperty.getRiot();;//"https://ddragon.leagueoflegends.com/api/versions.json";
        object = responseMapper.getRiotData(requestURL, MatchDetailDto.class);
        MatchDetailDto matchDetail =  (MatchDetailDto) object;
        System.out.println("ㅋㅋ"+matchDetail);
        model.addAttribute("matchDetail", matchDetail);
        return "summoner";
    }


    @GetMapping("/hello")
    public String hello() {
        return "footer";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @PostMapping("/test")
    public String test(@ModelAttribute("ggggg") Info info){
        return info.getName() + " AND " + info.getId();
    }

    @PostMapping("/test2")
    public String blogPost(@RequestBody Info info){
        return info.getName() + " AND " + info.getId();
    }
}
