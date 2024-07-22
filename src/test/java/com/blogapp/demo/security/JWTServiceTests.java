package com.blogapp.demo.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JWTServiceTests {
    @Autowired
    private JWTService jwtService;

    @Test
    void canCreateJWTFromUserId()
    {
        var jwt=jwtService.createJWT(1001L);
        assertNotNull(jwt);
    }
}
