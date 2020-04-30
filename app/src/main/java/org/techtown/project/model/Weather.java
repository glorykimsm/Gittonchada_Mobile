package org.techtown.project.model;

import android.content.Intent;

import java.util.ArrayList;

public class Weather {

    public String timezone;
    public Current current;
    public ArrayList<Daily> daily;

    public static class Current {
        public Long dt;
        public Long sunrise;
        public Long sunset;
        public Double temp;
        public Double feels_like;
        public Integer pressure;
        public Integer humidity;
        public Double dew_point;
        public Double uvi;
        public Integer clouds;
        public Integer visibility;
        public Double wind_speed;
        public Integer wind_deg;
    }

    public static class Daily {
        public Long dt;
        public Long sunrise;
        public Long sunset;
        public Temp temp;
    }

    public static class Temp {
        public Double day;
    }
}
