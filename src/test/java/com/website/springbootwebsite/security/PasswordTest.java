package com.website.springbootwebsite.security;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PasswordTest {

    private static PasswordHandler handler;

    @BeforeAll
    public static void passwordHandlerInstatiation() {
        handler = PasswordHandler.getInstance();
    }


    @Test
    void encryptionTest() {
        String encrypted = handler.encode("Hi my name is Kasra");
        assertTrue(handler.isMatch(encrypted, "Hi my name is Kasra"));
    }

}
