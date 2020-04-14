package com.csc510.smartweather.controller;

import com.csc510.smartweather.mapper.AdvertisementMapper;
import com.csc510.smartweather.model.Advertisement;
import com.csc510.smartweather.model.Seller;
import com.csc510.smartweather.model.User;
import com.csc510.smartweather.service.AdvertisementService;
import com.csc510.smartweather.service.SellerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Heng Yu
 * @date 4/11/20 10:29 PM
 */

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new RuntimeException();
        }
        if (user.getIsSeller()) {
            Seller dbSeller = sellerService.selectByUserId(user.getId());
            model.addAttribute("seller", dbSeller);
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam(value = "id", required = false) Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("type") String type,
                               @RequestParam("street") String street,
                               @RequestParam("city") String city,
                               @RequestParam("state") String state,
                               @RequestParam("country") String country,
                               @RequestParam("description") String description,
                               HttpServletRequest request,
                               Model model) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        Seller seller = new Seller();
        seller.setId(id);
        seller.setUserId(userId);
        seller.setName(name);
        seller.setType(type);
        seller.setStreet(street);
        seller.setCity(city);
        seller.setState(state);
        seller.setCountry(country);
        seller.setDescription(description);
        seller.setCreatedAt(System.currentTimeMillis());
        seller.setUpdatedAt(seller.getCreatedAt());

        //验证数据完整性
        if (StringUtils.isBlank(name)) {
            model.addAttribute("seller", seller);
            model.addAttribute("error", "Shop name cannot be blank!");
            return "register";
        } else if (StringUtils.isBlank(type)) {
            model.addAttribute("seller", seller);
            model.addAttribute("error", "Category cannot be blank!");
            return "register";
        } else if (StringUtils.isBlank(street)) {
            model.addAttribute("seller", seller);
            model.addAttribute("error", "Street cannot be blank!");
            return "register";
        } else if (StringUtils.isBlank(city)) {
            model.addAttribute("seller", seller);
            model.addAttribute("error", "City cannot be blank!");
            return "register";
        } else if (StringUtils.isBlank(state)) {
            model.addAttribute("seller", seller);
            model.addAttribute("error", "State cannot be blank!");
            return "register";
        } else if (StringUtils.isBlank(country)) {
            model.addAttribute("seller", seller);
            model.addAttribute("error", "Country cannot be blank!");
            return "register";
        }

        sellerService.createOrUpdateSeller(seller, user);
        return "redirect:/";
    }

    @GetMapping("/publish")
    public String getPublish(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //抛出未登录异常
            throw new RuntimeException();
        } else if (!user.getIsSeller()) {
            //抛出权限异常
            throw new RuntimeException();
        }
        Seller seller = sellerService.selectByUserId(user.getId());
        model.addAttribute("seller", seller);
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String showPublish(@PathVariable("id") Integer id, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //抛出未登录异常
            throw new RuntimeException();
        } else if (!user.getIsSeller()) {
            //抛出权限异常
            throw new RuntimeException();
        }
        Advertisement adAd = advertisementService.selectById(id);
        Seller dbSeller = sellerService.selectById(adAd.getSellerId());
        if (!dbSeller.getUserId().equals(user.getId())) {
            //抛出权限异常
            throw new RuntimeException();
        }
        model.addAttribute("ad", adAd);
        model.addAttribute("seller", dbSeller);
        return "publish";
    }

    @PostMapping("/publish")
    public String postPublish(@RequestParam(value = "id", required = false) Integer id,
                              @RequestParam("sellerId") Integer sellerId,
                              @RequestParam("sellerCity") String sellerCity,
                              @RequestParam("title") String title,
                              @RequestParam("description") String description,
                              Model model) {
        Advertisement ad = new Advertisement();
        ad.setId(id);
        ad.setTitle(title);
        ad.setDescription(description);
        ad.setSellerId(sellerId);
        ad.setSellerCity(sellerCity);
        ad.setCreatedAt(System.currentTimeMillis());
        ad.setUpdatedAt(ad.getCreatedAt());

        //验证数据完整性
        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "Title cannot be blank!");
            Seller dbSeller = sellerService.selectById(sellerId);
            model.addAttribute("seller", dbSeller);
            model.addAttribute("ad", ad);
            return "publish";
        }

        advertisementService.createOrUpdate(ad);
        return "redirect:/";
    }

    @GetMapping("/my-advertisements")
    public String index(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //抛出未登录异常
            throw new RuntimeException();
        } else if (!user.getIsSeller()) {
            //抛出权限异常
            throw new RuntimeException();
        }
        Seller seller = sellerService.selectByUserId(user.getId());
        List<Advertisement> dbAds = advertisementMapper.selectBySellerId(seller.getId());
        model.addAttribute("ads", dbAds);
        return "advertisements";
    }

    @GetMapping("/profile")
    public String myProfile(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //抛出未登录异常
            throw new RuntimeException();
        } else if (!user.getIsSeller()) {
            //抛出权限异常
            throw new RuntimeException();
        }
        Seller dbSeller = sellerService.selectByUserId(user.getId());
        model.addAttribute("seller", dbSeller);
        model.addAttribute("category", "seller");
        return "seller-profile";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Integer id, Model model) {
        Seller dbSeller = sellerService.selectById(id);
        model.addAttribute("seller", dbSeller);
        model.addAttribute("category", "user");
        return "seller-profile";
    }
}
