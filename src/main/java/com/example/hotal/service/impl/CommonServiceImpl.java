package com.example.hotal.service.impl;

import com.example.hotal.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;

@Service
public class CommonServiceImpl implements CommonService {

    private static final String BOT_API_URL = "https://api.ownthink.com/bot?appid=91665c18ddc24030a56570800f58aa5c&userid=15570200352&spoken=";

    @Override
    public String getNotice() {
        return "message";
    }

    @Override
    public String getBotResponse(String userMessage) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BOT_API_URL + userMessage;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        JSONObject responseBody = new JSONObject(response.getBody());
        String botResponse = responseBody.getJSONObject("data").getJSONObject("info").getString("text");

        return botResponse;
    }
}