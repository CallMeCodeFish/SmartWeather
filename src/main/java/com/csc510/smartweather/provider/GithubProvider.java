package com.csc510.smartweather.provider;

import com.alibaba.fastjson.JSON;
import com.csc510.smartweather.dto.GithubPostDTO;
import com.csc510.smartweather.dto.GithubUserDTO;
import com.csc510.smartweather.enumerate.AccountTypeEnum;
import com.csc510.smartweather.model.User;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Heng Yu
 * @date 4/10/20 5:51 AM
 */

@Component
public class GithubProvider {
    public String getAccessToken(String code) {
        String clientId = "6d8d6fd89d1a1fefba72";
        String clientSecret = "15d83057f6a4fbdb63ece88d93808f565f26f88c";
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        GithubPostDTO githubPostDTO = new GithubPostDTO();
        githubPostDTO.setClient_id(clientId);
        githubPostDTO.setClient_secret(clientSecret);
        githubPostDTO.setCode(code);
        String json = JSON.toJSONString(githubPostDTO);
        String url = "https://github.com/login/oauth/access_token";
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            String accessTokenEntry = responseBody.split("&")[0];
            String accessToken = accessTokenEntry.split("=")[1];
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUserDTO getUserInfo(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.github.com/user";
        String value = "token " + accessToken;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", value)
                .build();
        Headers headers = request.headers();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            GithubUserDTO githubUserDTO = JSON.parseObject(responseBody, GithubUserDTO.class);
            return githubUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
