package com.csc510.smartweather.mapper;

import com.csc510.smartweather.model.WeatherCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WeatherCodesMapper {
    @Select("select * from weather_codes where code=#{weather_code}")
    WeatherCode selectByWeatherCode(int weather_code);
}
