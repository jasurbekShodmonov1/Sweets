package com.example.sweets.service.auth;


import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlacklistService {

    private final Set<String> blacklistedTokens = new HashSet<>();

    public void blackListToken(String token){
        blacklistedTokens.add(token);
    }

    public boolean isBlackListed(String token){
        return blacklistedTokens.contains(token);
    }
}
