package com.blog.demo.provider;

import com.alibaba.fastjson.JSON;
import com.blog.demo.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType js
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(js, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {

                String string = response.body().string();
                System.out.println(string);
                String[] split = string.split("&");
                String s = split[0];
                String[] split1 = s.split("=");
                String s1 = split1[1];
                System.out.println(s1);
                return s1;
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }

    public GithubUser getUser(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                                .url("https://api.github.com/user?access_token="+accessToken)
                                .build();
        Response response = client.newCall(request).execute();
        String str = response.body().string();
        GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
        return githubUser;
    }
}
//
