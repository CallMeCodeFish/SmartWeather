package com.csc510.smartweather.mapper;

import com.csc510.smartweather.model.Recommendation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RecommendationMapper {
    @Select("select * from recommendation where weather=#{weather}")
    List<Recommendation> selectByWeather(String weather);
}
