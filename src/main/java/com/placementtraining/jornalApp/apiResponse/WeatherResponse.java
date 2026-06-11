package com.placementtraining.jornalApp.apiResponse;

import lombok.Data;
import java.util.List;

@Data
public class WeatherResponse {

    private String name;      // City name
    private Main main;
    private Wind wind;
    private List<Weather> weather;

    @Data
    public static class Main {
        private double temp;
        private int humidity;
        private int pressure;
    }

    @Data
    public static class Wind {
        private double speed;
    }

    @Data
    public static class Weather {
        private String main;
        private String description;
    }
}