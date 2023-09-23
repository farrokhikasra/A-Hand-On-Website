package com.website.springbootwebsite.security;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.logging.Handler;

public class PasswordHandler {
    private static PasswordHandler handler;
    private SCryptPasswordEncoder encoder;

    private PasswordHandler() {
        int cpuCost = (int) Math.pow(2, 14); // factor to increase CPU costs
        int memoryCost = 8; // increases memory usage
        int parallelization = 1; // currently not supported by Spring Security
        int keyLength = 32; // key length in bytes
        int saltLength = 64; // salt length in bytes
        encoder = new SCryptPasswordEncoder(cpuCost, memoryCost, parallelization, keyLength, saltLength);
    }

    public String encode(String password) {
        return encoder.encode(password);
    }

    public boolean isMatch(String encodedPassword, String password) {
        return encoder.matches(password, encodedPassword);
    }


    public static PasswordHandler getInstance() {
        if (handler == null) {
            handler = new PasswordHandler();
            return handler;
        }
        return handler;
    }


}
