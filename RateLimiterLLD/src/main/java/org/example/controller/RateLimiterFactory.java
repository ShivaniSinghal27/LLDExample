package org.example.controller;

import org.example.service.IRateLimiterInterface;
import org.example.service.impl.FixedWindowRateLimiter;

public class RateLimiterFactory {

    public static IRateLimiterInterface createRateLimiter(String type, long windowTimeSec, int allowedRequests) {
            switch(type) {
                case "Fixed":
                    return new FixedWindowRateLimiter(allowedRequests, windowTimeSec*1000);

                default:
                    throw new RuntimeException("Not support type:" + type);
            }
    }
}
