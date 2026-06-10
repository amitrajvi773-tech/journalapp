package com.placementtraining.jornalApp.apiResponse;

import lombok.Data;
import java.util.List;

@Data
public class WeatherResponse {

    private Main main;
    private List<Weather> weather;
    private String name;

    @Data
    public static class Main {
        private double temp;
    }

    @Data
    public static class Weather {
        private String main;
        private String description;
    }
}