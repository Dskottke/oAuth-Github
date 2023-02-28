package com.example.backend.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubResponse(@JsonProperty("access_token") String accessToken) {

}
