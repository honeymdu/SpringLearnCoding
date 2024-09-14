package com.example.SpringLearnH2db.Handlers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.example.SpringLearnH2db.Entitys.User;
import com.example.SpringLearnH2db.Services.JwtService;
import com.example.SpringLearnH2db.Services.SessionService;
import com.example.SpringLearnH2db.Services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;
    private final SessionService sessionService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) token.getPrincipal();
        log.info(oauth2User.getAttribute("email"));
        log.info(oauth2User.getAttribute("name"));

        String name = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");

        User user = userService.loadUserByEmail(email);

        if (user == null) {
            User newUser = User.builder()
                    .email(email)
                    .name(name)
                    .build();
            userService.save(newUser);
        }
        User savedUser = userService.loadUserByEmail(email);
        String Accesstoken = jwtService.GenerateAccessToken(savedUser);
        String Refreshtoken = jwtService.GenerateRefreshToken(savedUser);
        sessionService.GenerateNewSession(savedUser, Refreshtoken);
        
        Cookie cookie = new Cookie("refreshToken", Refreshtoken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        String frontendUrl = "http://localhost:8080/home.html?token=" + Accesstoken;
        getRedirectStrategy().sendRedirect(request, response, frontendUrl);

    }

}
