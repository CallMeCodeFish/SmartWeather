package com.csc510.smartweather.weatherInfo;

public class CurrentWeather {
    private float temp;
    private float feel_like;
    private float temp_min;
    private float temp_max;
    private int pressure;
    private int humidity;
    private int id;
    private String mainInfo;
    private String icon;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeel_like() {
        return feel_like;
    }

    public void setFeel_like(float feel_like) {
        this.feel_like = feel_like;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(String mainInfo) {
        this.mainInfo = mainInfo;
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
                ", id=" + id +
                ", mainInfo='" + mainInfo + '\'' +
                '}';
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }
}
