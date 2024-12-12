package com.Bakunov.OOPpractice;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {

    @Value("${admin.username}")
    private String adminUsername;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        
        if ((path.startsWith("/lessons") || path.startsWith("/days")) && !request.getMethod().equals("GET")) {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Token is missing");
                return;
            }

            try {
                String decodedToken = new String(Base64.getDecoder().decode(authHeader));
                String[] parts = decodedToken.split(":");
                String username = parts[0];
                long expirationTime = Long.parseLong(parts[1]);

                if (!adminUsername.equals(username) || System.currentTimeMillis() > expirationTime) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Unauthorized: Invalid or expired token");
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Malformed token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

