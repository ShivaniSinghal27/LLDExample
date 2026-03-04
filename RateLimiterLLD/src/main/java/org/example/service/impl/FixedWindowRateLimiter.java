package org.example.service.impl;

import org.example.service.IRateLimiterInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter implements IRateLimiterInterface {

    private long startWindow;
    private Map<String, Integer> requestCounts = new ConcurrentHashMap<>();
    private Integer allowedRequests;
    private long windowSizeMillis;

    public FixedWindowRateLimiter( Integer allowedRequests, long windowSizeMillis) {
        this.startWindow = System.currentTimeMillis();
        this.allowedRequests = allowedRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public synchronized boolean allowRequest(String userId) {
         long currentTime = System.currentTimeMillis();
         if(currentTime - this.startWindow >= this.windowSizeMillis) {
              requestCounts.remove(userId);
              startWindow = System.currentTimeMillis();
         }

         requestCounts.put(userId, requestCounts.getOrDefault(userId, 0) + 1);
         return requestCounts.get(userId) <= this.allowedRequests;
    }
}
