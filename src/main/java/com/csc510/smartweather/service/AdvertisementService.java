package com.csc510.smartweather.service;

import com.csc510.smartweather.dto.AdvertisementDTO;
import com.csc510.smartweather.mapper.AdvertisementMapper;
import com.csc510.smartweather.model.Advertisement;
import com.csc510.smartweather.model.Seller;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<AdvertisementDTO> selectByCity(String city) {
        List<AdvertisementDTO> res = new ArrayList<>();
        List<Advertisement> dbAdsList = advertisementMapper.selectByCity(city);
        Map<Integer, Seller> sellers = new HashMap<>();
        for (Advertisement ad : dbAdsList) {
            Integer sellerId = ad.getSellerId();
            if (!sellers.containsKey(sellerId)) {
                Seller dbSeller = sellerService.selectById(sellerId);
                sellers.put(sellerId, dbSeller);
            }
            Seller dbSeller = sellers.get(sellerId);
            AdvertisementDTO adDTO = new AdvertisementDTO();
            BeanUtils.copyProperties(ad, adDTO);
            adDTO.setSeller(dbSeller);
            res.add(adDTO);
        }
        return res;
    }
}
