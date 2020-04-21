package com.csc510.smartweather.controller;

import com.csc510.smartweather.dto.AdvertisementDTO;
import com.csc510.smartweather.enumerate.CustomizeExceptionEnum;
import com.csc510.smartweather.exception.CustomizeException;
import com.csc510.smartweather.service.AdvertisementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String nearbyAdvertisement(HttpServletRequest request, Model model) {
        String city = (String) request.getSession().getAttribute("city");
        if (city == null || StringUtils.isBlank(city)) {
            throw new CustomizeException(CustomizeExceptionEnum.NO_LOCATION_INFO);
        }
        List<AdvertisementDTO> dbAds = advertisementService.selectByCity(city);
        model.addAttribute("ads", dbAds);
        model.addAttribute("title", "Nearby sales promotion");
        model.addAttribute("type", 1);
        return "advertisements";
    }

    @GetMapping("/{id}")
    public String getAdvertisement(@PathVariable("id") Integer id, Model model) {
        AdvertisementDTO ad = advertisementService.selectDTOById(id);
        model.addAttribute("ad", ad);
        return "show-advertisement";
    }
}
