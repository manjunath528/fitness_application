package com.gym.app.configuration;

import com.gym.app.entity.Token;
import com.gym.app.entity.User;
import com.gym.app.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TokenRepository tokenRepo;

    public JwtFilter(JwtService jwtService, TokenRepository tokenRepo) {
        this.jwtService = jwtService;
        this.tokenRepo = tokenRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Get token from DB and check if it's not expired
            Optional<Token> storedTokenOpt = tokenRepo.findByTokenAndExpiredFalse(jwt);

            if (storedTokenOpt.isPresent()) {
                Token storedToken = storedTokenOpt.get();
                User user = storedToken.getUser();

                boolean isValid = jwtService.validateToken(jwt,
                        new org.springframework.security.core.userdetails.User(
                                user.getUsername(),
                                user.getPassword(),
                                List.of(new SimpleGrantedAuthority(user.getRole().name()))
                        )
                );

                if (isValid) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            user, null, List.of(new SimpleGrantedAuthority(user.getRole().name()))
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
