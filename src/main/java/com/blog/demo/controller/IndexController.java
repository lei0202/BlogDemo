package com.blog.demo.controller;

import com.blog.demo.mapper.UserMapper;
import com.blog.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@org.springframework.stereotype.Controller
public class IndexController {

    @SuppressWarnings("all")
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String getIndex(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies
             ) {
            if(c.getName().equals("token")){
                String token = c.getValue();
                User user = userMapper.findByToken(token);
                if(user != null) {
                    request.getSession().setAttribute("user",user);
                }

                break;
            }
        }

        return "index";
    }
}
