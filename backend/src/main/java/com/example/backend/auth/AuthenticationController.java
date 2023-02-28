package com.example.backend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final GithubOAuthService githubOAuthService;

    @GetMapping("me")
    public String me() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(service.register(registerRequest));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(service.authenticate(authenticationRequest));

    }

    @PostMapping("oauth/github")
    public ResponseEntity<AuthenticationResponse> github(@RequestBody String code, HttpServletRequest request, HttpServletResponse response) {
        String accessToken = githubOAuthService.getGithubAccessToken(code);
        return ResponseEntity.ok(service.loginWithGithub("TestMe", accessToken, request, response));

    }

}
