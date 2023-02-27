package com.example.backend;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/")
@RequiredArgsConstructor
public class LoginController {

    private final GithubOAuthService githubOAuthService;
    private final UserService userService;

    @GetMapping
    public String github() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("oauth/github")
    public String github(@RequestBody String code, HttpServletResponse response, HttpServletRequest request) {
        String accessToken = githubOAuthService.loginWithGithub(code);
        return userService.loginWithGithub("TestMe", accessToken, request, response).getUsername();
    }
    @PostMapping("login")
    public String login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response, HttpServletRequest request) {
    return userService.loginWithApp(userLoginDTO, request, response).getUsername();
    }
    @GetMapping("logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
