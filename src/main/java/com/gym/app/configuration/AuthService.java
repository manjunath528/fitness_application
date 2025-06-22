package com.gym.app.configuration;

import com.google.common.base.Strings;
import com.gym.app.baseframework.exception.SystemException;
import com.gym.app.baseframework.exception.enums.ApiErrors;
import com.gym.app.entity.Token;
import com.gym.app.entity.User;
import com.gym.app.enums.Role;
import com.gym.app.repository.TokenRepository;
import com.gym.app.repository.UserRepository;
import com.gym.app.service.dto.RegistrationResponse;
import com.gym.app.service.dto.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService{
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
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
    public RegistrationResponse register(String username, String password) throws SystemException{
        if(Strings.isNullOrEmpty(username) ||Strings.isNullOrEmpty(password)){
            logger.error("signUp: Missing mandatory data -> {},{}", username,password);
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (userOptional.isPresent()) {
            logger.error("signUp: User already registered with name -> {}", username);
            throw new SystemException(ApiErrors.USER_ALREADY_EXISTS);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        userRepo.save(user);
        logger.info("User Registration Done Successfully->{}",username);
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setUsername(username);
        registrationResponse.setPassword(password);
        return registrationResponse;
    }

    public TokenResponse login(String username, String password) throws SystemException {
        if(Strings.isNullOrEmpty(username) ||Strings.isNullOrEmpty(password)){
            logger.error("signUp: Missing mandatory data -> {},{}", username,password);
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        Optional<User> user = userRepo.findByUsername(username);
        if (user == null) {
            logger.error("Login: User doesn't exist -> {}",username);
            throw new SystemException(ApiErrors.USER_ALREADY_EXISTS);
        }
        logger.info("User Details for authentication Received->{}",username);
        List<Token> allTokens = tokenRepo.findAllByUser(user.orElse(null));
        allTokens.forEach(token -> token.setExpired(true));
        tokenRepo.saveAll(allTokens);
        logger.info("User old tokens set expired->{}",username);
        String jwt = jwtService.generateToken(user.orElse(null));
        logger.info("Token has successfully generated-{}",jwt);
        Token newToken = new Token();
        newToken.setToken(jwt);
        newToken.setUser(user.orElse(null));
        newToken.setExpired(false);
        tokenRepo.save(newToken);
        logger.info("Token has successfully saved into database for -{}",username);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setUsername(username);
        tokenResponse.setToken(jwt);
        return tokenResponse;
    }


}
