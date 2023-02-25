package com.example.backend;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final SecurityContextRepository securityContextRepository;

    public UserService(UserRepo userRepo, SecurityContextRepository securityContextRepository) {
        this.userRepo = userRepo;
        this.securityContextRepository = securityContextRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUser mongoUser = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
        return new User(mongoUser.Username(), mongoUser.Password(), List.of());
    }

    public UserDetails loginWithGithub(String username, String accessToken, HttpServletRequest request, HttpServletResponse response) {
        User user = new User(username, accessToken, List.of());
        MongoUser mongoUser = new MongoUser(UUID.randomUUID().toString(), username, accessToken);
        userRepo.save(mongoUser);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, accessToken);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(token);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        return user;
    }
}