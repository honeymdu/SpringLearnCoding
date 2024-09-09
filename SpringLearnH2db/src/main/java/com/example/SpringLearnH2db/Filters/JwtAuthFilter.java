package com.example.SpringLearnH2db.Filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.SpringLearnH2db.Entitys.User;
import com.example.SpringLearnH2db.Services.JwtService;
import com.example.SpringLearnH2db.Services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader == null || ! requestTokenHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        String Token = requestTokenHeader.split("Bearer")[1];

        Long userId = jwtService.getUserIdFromToken(Token);

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.getUserFromId(userId);
            UsernamePasswordAuthenticationToken AuthenticationToken = new UsernamePasswordAuthenticationToken(user,
                    null, null);
            AuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);
        }

        filterChain.doFilter(request, response);

    }

}
