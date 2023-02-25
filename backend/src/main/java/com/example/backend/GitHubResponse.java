package com.example.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubResponse(@JsonProperty("access_token") String accessToken) {

}
