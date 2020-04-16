package com.csc510.smartweather.weatherInfo;

public class CurrentWeather {
    private int temp;
    private int feel_like;
    private int temp_min;
    private int temp_max;
    private int pressure;
    private int humidity;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getFeel_like() {
        return feel_like;
    }

    public void setFeel_like(int feel_like) {
        this.feel_like = feel_like;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(int temp_min) {
        this.temp_min = temp_min;
    }

    public int getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(int temp_max) {
        this.temp_max = temp_max;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "temp=" + temp +
                ", feel_like=" + feel_like +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
