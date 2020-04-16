package com.csc510.smartweather.weatherInfo;

public class WeatherForecast {
    private float [] temp = new float[24];
    private float [] feel_like = new float[24];
    private int [] pressure = new int[24];
    private int [] humidity = new int[24];

    public float [] getTemp() {
        return temp;
    }

    public void setTemp(float [] temp) {
        this.temp = temp;
    }

    public void setiTemp(float itemp, int i) {
        this.temp[i] = itemp;
    }

    public float [] getFeel_like() {
        return feel_like;
    }

    public void setFeel_like(float [] feel_like) {
        this.feel_like = feel_like;
    }

    public void setiFeel_like(float ifeel_like, int i) {
        this.feel_like[i] = ifeel_like;
    }

    public int [] getPressure() {
        return pressure;
    }

    public void setPressure(int [] pressure) {
        this.pressure = pressure;
    }

    public void setiPressure(int ipressure, int i) {
        this.pressure[i] = ipressure;
    }

    public int [] getHumidity() {
        return humidity;
    }

    public void setHumidity(int [] humidity) {
        this.humidity = humidity;
    }

    public void setiHumidity(int ihumidity, int i) {
        this.humidity[i] = ihumidity;
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
                    '}';
        }
        return weatherforecastinfo;
    }
}
