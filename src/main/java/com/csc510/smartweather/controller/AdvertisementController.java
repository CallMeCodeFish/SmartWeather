package com.csc510.smartweather.controller;

import com.csc510.smartweather.dto.AdvertisementDTO;
import com.csc510.smartweather.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Heng Yu
 * @date 4/12/20 8:24 PM
 */

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping
    public String nearbyAdvertisement() {
        //定位模式和非定位模式


        return "advertisements";
    }

    @GetMapping("/{id}")
    public String getAdvertisement(@PathVariable("id") Integer id, Model model) {
        AdvertisementDTO ad = advertisementService.selectDTOById(id);
        model.addAttribute("ad", ad);
        return "show-advertisement";
    }
}
