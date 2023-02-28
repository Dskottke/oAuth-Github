package com.example.backend.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service

public class GithubOAuthService {
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    private final WebClient webClient = WebClient.create();

    public String getGithubAccessToken(String code) {
        GitHubResponse gitResponse = Objects.requireNonNull(webClient.post()
                        .uri("https://github.com/login/oauth/access_token?code=" + code + "&client_id=" + clientId + "&client_secret=" + clientSecret)
                        .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(GitHubResponse.class)
                        .block())
                .getBody();

        assert gitResponse != null;


        return gitResponse.accessToken();
    }
}
