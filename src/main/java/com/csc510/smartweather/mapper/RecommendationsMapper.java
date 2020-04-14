package com.csc510.smartweather.mapper;

import com.csc510.smartweather.model.Recommendation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RecommendationsMapper {
    @Select("select * from recommendations where reco_id in " +
            "(select reco_id from weather_reco_mappings where code=#{weather_code})")
    List<Recommendation> selectByWeatherCode(int weather_code);
}
