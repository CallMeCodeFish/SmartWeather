package com.csc510.smartweather.service;

import com.csc510.smartweather.mapper.SellerMapper;
import com.csc510.smartweather.mapper.UserMapper;
import com.csc510.smartweather.model.Seller;
import com.csc510.smartweather.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Heng Yu
 * @date 4/12/20 4:11 PM
 */

@Service
public class SellerService {
    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private UserMapper userMapper;


    @Transactional
    public void createOrUpdateSeller(Seller seller, User user) {
        if (seller.getId() == null) {
            sellerMapper.insert(seller);
            user.setIsSeller(true);
            user.setUpdatedAt(seller.getCreatedAt());
            userMapper.update(user);
        } else {
            seller.setUpdatedAt(System.currentTimeMillis());
            sellerMapper.update(seller);
        }
    }

    public Seller selectByUserId(Integer userId) {
        List<Seller> dbSellerList = sellerMapper.selectByUserId(userId);
        return dbSellerList.get(0);
    }

    public Seller selectById(Integer id) {
        List<Seller> dbSellerList = sellerMapper.selectById(id);
        return dbSellerList.get(0);
    }
}
