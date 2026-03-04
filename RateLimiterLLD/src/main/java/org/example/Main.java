package org.example;

import org.example.service.impl.RateLimiterServiceImpl;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterServiceImpl service = new RateLimiterServiceImpl();
        service.registerUserRateLimiter("user1", "Fixed", 3, 2 );
        service.registerUserRateLimiter("user2", "Fixed", 3, 4 );

        for (int i = 0; i <= 5; i++) {
            System.out.println("User 1 Request " + (i + 1) + " : " + service.allowRequest("user1"));
            System.out.println("User 2 Request " + (i + 1) + " : " + service.allowRequest("user2"));
            Thread.sleep(1000);
        }
    }
}