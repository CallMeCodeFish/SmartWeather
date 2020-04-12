package com.csc510.smartweather.mapper;

import com.csc510.smartweather.model.Recommendation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecommendationMapper {
    @Select("select * from user where weather=#{weather} and type=#{type}")
    Recommendation selectByWeatherAndType(String weather, String type);
}
