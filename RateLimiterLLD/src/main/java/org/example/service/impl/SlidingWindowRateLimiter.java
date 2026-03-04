package org.example.service.impl;

import org.example.service.IRateLimiterInterface;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements IRateLimiterInterface {
    private long windowSize;
    private int allowedRequests;
    Map<String, Deque<Long>> map = new ConcurrentHashMap<>();


    public SlidingWindowRateLimiter(int allowedRequests, long windowSize ) {
        this.windowSize = windowSize;
        this.allowedRequests = allowedRequests;
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        map.putIfAbsent(userId, new LinkedList<>());
        Deque<Long> timestamps = map.get(userId);

        while(!timestamps.isEmpty() && currentTime-timestamps.peek() >  windowSize) {
            timestamps.pollFirst();
        }

        if(timestamps.size() < allowedRequests) {
            timestamps.addLast(currentTime);
            return true;
        }

        return false;
    }
}
