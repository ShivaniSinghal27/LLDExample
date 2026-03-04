package org.example.service;

public interface IRateLimiterInterface {

    public boolean allowRequest(String userId);
}
