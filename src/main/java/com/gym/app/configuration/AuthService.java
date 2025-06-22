package com.gym.app.configuration;

import com.gym.app.entity.Token;
import com.gym.app.entity.User;
import com.gym.app.enums.Role;
import com.gym.app.repository.TokenRepository;
import com.gym.app.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepo;
    private final AuthenticationManager authenticationManager;
    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService,
                       TokenRepository tokenRepo, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepo = tokenRepo;
        this.authenticationManager = authenticationManager;
    }
    public String register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        userRepo.save(user);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Token> allTokens = tokenRepo.findAllByUser(user);
        allTokens.forEach(token -> token.setExpired(true));
        tokenRepo.saveAll(allTokens);
        System.out.println("Expired old tokens for user: " + username);

        String jwt = jwtService.generateToken(user);
        System.out.println("Generated token: " + jwt);

        Token newToken = new Token();
        newToken.setToken(jwt);
        newToken.setUser(user);
        newToken.setExpired(false);

        tokenRepo.save(newToken);
        System.out.println("Saved new token for user: " + username);

        return jwt;
    }


}
