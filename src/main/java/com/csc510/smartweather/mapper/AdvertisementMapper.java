package com.csc510.smartweather.mapper;

import com.csc510.smartweather.model.Advertisement;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Heng Yu
 * @date 4/12/20 5:43 PM
 */

@Mapper
@Component
public interface AdvertisementMapper {

    @Insert("insert into advertisement (title, description, seller_id, seller_city, created_at, updated_at) values (#{title}, #{description}, #{sellerId}, #{sellerCity}, #{createdAt}, #{updatedAt})")
    void insert(Advertisement ad);

    @Select("select * from advertisement where seller_id=#{sellerId} order by created_at desc")
    List<Advertisement> selectBySellerId(@Param("sellerId") Integer sellerId);

    @Select("select * from advertisement where id=#{id}")
    List<Advertisement> selectById(@Param("id") Integer id);

    @Update("update advertisement set title=#{title}, description=#{description}, seller_city=#{sellerCity}, updated_at=#{updatedAt} where id=#{id}")
    void update(Advertisement ad);
}
