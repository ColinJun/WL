package com.WL.web;

import com.WL.web.dto.MatchDto;
import com.WL.web.dto.SummonerDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResponseMapper {
    private static final Logger logger = LoggerFactory.getLogger(ResponseMapper.class);
    public Object getRiotData(String requestURL, Class classDto){
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = null;
        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성

            HttpResponse response = client.execute(getRequest);
            System.out.println(classDto);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                object = objectMapper.readValue(body, classDto); //Dto 객체 타입에 따라 deserialize error 날 수 있음.
                return object;
            }
        } catch (Exception e){
            logger.info(e.toString());
        }
        return object;
    }
}
