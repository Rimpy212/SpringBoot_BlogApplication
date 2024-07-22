package com.blogapp.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    //TODO: Move the key to a separate .properties file (not in git)
    private static final String JWT_KEY="jja3853jof395nkaya560gsnkq5w56g";
    private Algorithm algorithm=Algorithm.HMAC256(JWT_KEY);

    public String createJWT(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                //.withExpiresAt( TODO: setup an expiry parameter
                .sign(algorithm);
    }

    public Long retrieveUserId(String jwtString)
    {
            var decodedJWT = JWT.decode(jwtString);
            var userId=Long.valueOf(decodedJWT.getSubject());
            return userId;
    }

}
