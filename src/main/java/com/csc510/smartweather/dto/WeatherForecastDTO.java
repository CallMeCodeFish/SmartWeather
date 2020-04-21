package com.csc510.smartweather.dto;

public class WeatherForecastDTO {
    private float [] temp = new float[24];
    private float [] feel_like = new float[24];
    private int [] pressure = new int[24];
    private int [] humidity = new int[24];
    private float [] dew_point = new float[24];
    private int [] clouds = new int[24];
    private float [] wind_speed = new float[24];

    public float [] getTemp() {
        return temp;
    }

    public void setTemp(float [] temp) {
        this.temp = temp;
    }

    public void setTemp(float itemp, int i) {
        this.temp[i] = itemp;
    }

    public float [] getFeel_like() {
        return feel_like;
    }

    public void setFeel_like(float [] feel_like) {
        this.feel_like = feel_like;
    }

    public void setFeel_like(float ifeel_like, int i) {
        this.feel_like[i] = ifeel_like;
    }

    public int [] getPressure() {
        return pressure;
    }

    public void setPressure(int [] pressure) {
        this.pressure = pressure;
    }

    public void setPressure(int ipressure, int i) {
        this.pressure[i] = ipressure;
    }

    public int [] getHumidity() {
        return humidity;
    }

    public void setHumidity(int [] humidity) {
        this.humidity = humidity;
    }

    public void setHumidity(int ihumidity, int i) {
        this.humidity[i] = ihumidity;
    }

    public float [] getDew_point() {
        return dew_point;
    }

    public void setDew_point(float [] dew_point) {
        this.dew_point = dew_point;
    }

    public void setDew_point(float idew_point, int i) {
        this.dew_point[i] = idew_point;
    }

    public int [] getClouds() {
        return clouds;
    }

    public void setClouds(int [] clouds) {
        this.clouds = clouds;
    }

    public void setClouds(int iclouds, int i) {
        this.clouds[i] = iclouds;
    }

    public float [] getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float [] wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setWind_speed(float iwind_speed, int i) {
        this.wind_speed[i] = iwind_speed;
    }


    @Override
    public String toString() {
        String weatherforecastinfo = "WeatherForecast  ";
        for (int i = 0; i < 24; i++) {
            weatherforecastinfo += i + ":{" +
                    "temp=" + temp[i] +
                    ", feel_like=" + feel_like[i] +
                    ", pressure=" + pressure[i] +
                    ", humidity=" + humidity[i] +
                    ", dew_point=" + dew_point[i] +
                    ", clouds=" + clouds[i] +
                    ", wind_speed=" + wind_speed[i] +
                    '}';
        }
        return weatherforecastinfo;
    }
}
