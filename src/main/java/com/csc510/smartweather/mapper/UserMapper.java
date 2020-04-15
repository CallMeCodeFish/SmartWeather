package com.csc510.smartweather.mapper;

import com.csc510.smartweather.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Heng Yu
 * @date 4/10/20 8:53 PM
 */

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where account_type=#{accountType} and account_id=#{accountId}")
    List<User> selectByAccount(User user);

    @Insert("insert into user (name, account_type, account_id, avatar_url, token, is_seller, created_at, updated_at) values (#{name}, #{accountType}, #{accountId}, #{avatarUrl}, #{token}, #{isSeller}, #{createdAt}, #{updatedAt})")
    void insert(User user);

    @Update("update user set name=#{name}, avatar_url=#{avatarUrl}, token=#{token}, is_seller=#{isSeller}, updated_at=#{updatedAt} where id=#{id}")
    void update(User user);

    @Select("select * from user where token=#{token}")
    List<User> selectByToken(User user);
}
