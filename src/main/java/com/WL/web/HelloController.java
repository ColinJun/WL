package com.WL.web;

import com.WL.web.dto.HelloResponseDto;
import com.WL.web.dto.Info;
import com.WL.web.dto.SummonerDto;
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
import org.springframework.web.bind.annotation.*;

@ConfigurationProperties("key")
@RequiredArgsConstructor
@Controller
public class HelloController {
    @Autowired
    ConfigProperty configProperty;

    @GetMapping("/")
    public String index() {
        ObjectMapper objectMapper = new ObjectMapper();
        SummonerDto summoner = null;	// DTO
        String SummonerName = "허브밀크";//name.replaceAll(" ", "%20");
        String requestURL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ SummonerName + "?api_key=" + configProperty.getRiot();
        System.out.println(requestURL);
        String index = "";
        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성

            HttpResponse response = client.execute(getRequest);


            //Response 출력
            System.out.println(response);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                summoner = objectMapper.readValue(body, SummonerDto.class);   // String to Object로 변환
                //System.out.println(summoner);
                index = summoner.getName()+"님의 소환사 레벨은 "+summoner.getSummonerLevel()+"입니다";
                System.out.println(summoner.getName()+"님의 소환사 레벨은 "+summoner.getSummonerLevel()+"입니다");
            }
        } catch (Exception e){
            System.err.println(e.toString());
        }



        return "index";
    }

    @RequestMapping(value="/player/{id}", method=RequestMethod.GET)
    public Player getPlayers(@PathVariable("id") final String id) throws Exception{

        Player player;

        if(RestApplication.PlayerHm.containsKey(id)) {
            player = RestApplication.PlayerHm.get(id);
        } else {
            throw new Exception("Player " + id + "does not exists");
        }

        return player;
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
