package com.blog.demo.controller;

import com.blog.demo.dto.AccessTokenDTO;
import com.blog.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8888/callback");
        accessTokenDTO.setClient_id("35c4a4d61c5614daa6cf");
        accessTokenDTO.setClient_secret("1087239773f9a606673fdf7ed85dc09dbabbd349");
        githubProvider.getAccessToken(accessTokenDTO);
        return "index";
    }
}
