package com.csc510.smartweather.interceptor;

import com.csc510.smartweather.mapper.UserMapper;
import com.csc510.smartweather.model.User;
import com.csc510.smartweather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Heng Yu
 * @date 4/10/20 10:52 PM
 */

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                for (Cookie cookie : cookies) {
                    if ("sw-token".equals(cookie.getName())) {
                        user = new User();
                        String token = cookie.getValue();
                        user.setToken(token);
                        List<User> users = userService.selectByToken(user);
                        if (!users.isEmpty()) {
                            request.getSession().setAttribute("user", users.get(0));
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }
}
