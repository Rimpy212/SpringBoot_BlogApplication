package com.blogapp.demo.security;

import com.blogapp.demo.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticateManager implements AuthenticationManager {
    private JWTService jwtService;
    private UserService userService;

    public JWTAuthenticateManager(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication)
        {
            var jwtAuthentication = (JWTAuthentication) authentication;
            var jwt=jwtAuthentication.getCredentials();
            var userId=jwtService.retrieveUserId(jwt);

            var userEntity=userService.getUserById(userId);
            jwtAuthentication.userEntity=userEntity;
            jwtAuthentication.setAuthenticated(true);
            return jwtAuthentication;
        }

        return null;
    }
}
