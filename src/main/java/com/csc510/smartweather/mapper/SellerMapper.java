package com.csc510.smartweather.mapper;

import com.csc510.smartweather.model.Seller;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Heng Yu
 * @date 4/12/20 4:03 PM
 */

@Mapper
@Component
public interface SellerMapper {
    @Insert("insert into seller (user_id, name, type, street, city, state, country, description, created_at, updated_at) values (#{userId}, #{name}, #{type}, #{street}, #{city}, #{state}, #{country}, #{description}, #{createdAt}, #{updatedAt})")
    void insert(Seller seller);

    @Select("select * from seller where user_id=#{userId}")
    List<Seller> selectByUserId(@Param("userId") Integer userId);

    @Select("select * from seller where id=#{id}")
    List<Seller> selectById(@Param("id") Integer id);

    @Update("update seller set name=#{name}, type=#{type}, street=#{street}, city=#{city}, state=#{state}, country=#{country}, description=#{description}, updated_at=#{updatedAt} where id=#{id}")
    void update(Seller seller);
}
