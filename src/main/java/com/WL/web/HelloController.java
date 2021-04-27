package com.WL.web;

import com.WL.web.dto.HelloResponseDto;
import com.WL.web.dto.Info;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
public class HelloController {
    @Value("${Key}")
    private String Key;

    @GetMapping("/")
    public String index() {
        String SummonerName = "hideOnBush";//name.replaceAll(" ", "%20");
        String requestURL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ SummonerName + "?api_key=" + Key;

        System.out.println("하하하"+Key);
        System.out.println("하하하"+requestURL);
        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성

            HttpResponse response = client.execute(getRequest);

            //Response 출력
            System.out.println(response);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println("ㅎㅎ"+body);
                //summoner = objectMapper.readValue(body, Summoner.class);   // String to Object로 변환

            }
        } catch (Exception e){
            System.err.println("하하하"+e.toString());
        }



        return "index";
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
