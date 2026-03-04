package org.example.service.impl;

import org.example.controller.RateLimiterFactory;
import org.example.service.IRateLimiterInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterServiceImpl {
    Map<String, IRateLimiterInterface> userRateLimiters = new ConcurrentHashMap<>();

    public void registerUserRateLimiter(String userId, String type, long windowTimeSec, int allowedRequests) {
        userRateLimiters.put(userId, RateLimiterFactory.createRateLimiter(type, windowTimeSec, allowedRequests) );
    }

    public boolean allowRequest(String userId) {
        IRateLimiterInterface rateLimiter = userRateLimiters.get(userId);
        if (rateLimiter == null) throw new IllegalArgumentException("User not registered");
        return rateLimiter.allowRequest(userId);
    }
}
