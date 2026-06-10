package com.placementtraining.jornalApp.service;

import com.placementtraining.jornalApp.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {
    private static final String apikey="62a2da6f1e7bcc57060e795c33a4e2c2";
    private static final String API="https://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=YOUR_KEY&query=CITY";

    @Autowired
    RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalapi=API.replace("YOUR_KEY",apikey).replace("CITY",city);
       ResponseEntity<WeatherResponse> response= restTemplate.exchange(finalapi, HttpMethod.GET,null, WeatherResponse.class);
       WeatherResponse body=  response.getBody();
       return body;
    }
}
