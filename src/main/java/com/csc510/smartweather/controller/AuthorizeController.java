package com.csc510.smartweather.controller;

import com.csc510.smartweather.dto.GithubUserDTO;
import com.csc510.smartweather.enumerate.AccountTypeEnum;
import com.csc510.smartweather.model.User;
import com.csc510.smartweather.provider.GithubProvider;
import com.csc510.smartweather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Heng Yu
 * @date 4/10/20 5:19 AM
 */

@Controller
@RequestMapping("/callback")
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @GetMapping("/github")
    public String githubCallback(@RequestParam("code") String code, HttpServletResponse response, HttpServletRequest request) {
        String accessToken = githubProvider.getAccessToken(code);
        GithubUserDTO githubUserDTO = githubProvider.getUserInfo(accessToken);
        if (githubUserDTO != null && githubUserDTO.getId() != null) {
            User user = new User();
            user.setName(githubUserDTO.getLogin());
            user.setAccountType(AccountTypeEnum.GITHUB.getType());
            user.setAccountId(githubUserDTO.getId());
            user.setAvatarUrl(githubUserDTO.getAvatar_url());
            userService.createOrUpdateUser(user);
            Cookie cookie = new Cookie("sw-token", user.getToken());
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "redirect:/";
    }
}
