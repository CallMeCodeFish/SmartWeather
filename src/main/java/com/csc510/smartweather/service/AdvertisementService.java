package com.csc510.smartweather.service;

import com.csc510.smartweather.dto.AdvertisementDTO;
import com.csc510.smartweather.mapper.AdvertisementMapper;
import com.csc510.smartweather.model.Advertisement;
import com.csc510.smartweather.model.Seller;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heng Yu
 * @date 4/12/20 8:30 PM
 */

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private SellerService sellerService;

    public Advertisement selectById(Integer id) {
        List<Advertisement> dbAdsList = advertisementMapper.selectById(id);
        return dbAdsList.get(0);
    }

    public void createOrUpdate(Advertisement ad) {
        if (ad.getId() == null) {
            //创建
            advertisementMapper.insert(ad);
        } else {
            //更新
            ad.setUpdatedAt(System.currentTimeMillis());
            advertisementMapper.update(ad);
        }
    }

    public AdvertisementDTO selectDTOById(Integer id) {
        AdvertisementDTO adDTO = new AdvertisementDTO();
        List<Advertisement> dbAdsList = advertisementMapper.selectById(id);
        Advertisement dbAd = dbAdsList.get(0);
        Seller dbSeller = sellerService.selectById(dbAd.getSellerId());
        BeanUtils.copyProperties(dbAd, adDTO);
        adDTO.setSeller(dbSeller);
        return adDTO;
    }
}
