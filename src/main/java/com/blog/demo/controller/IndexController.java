package com.blog.demo.controller;

import com.blog.demo.dto.PaginationDTO;
import com.blog.demo.mapper.UserMapper;
import com.blog.demo.model.User;
import com.blog.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@org.springframework.stereotype.Controller
public class IndexController {

    @SuppressWarnings("all")
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String getIndex(HttpServletRequest request,
                           Model model,
                           @RequestParam(name = "page",defaultValue = "1") Integer page,
                           @RequestParam(name = "size",defaultValue = "10") Integer size) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null || cookies.length!=0)
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

        PaginationDTO paginationDTOlist = questionService.list(page,size);
        model.addAttribute("paginationDTOlist",paginationDTOlist);
        return "index";
    }
}
