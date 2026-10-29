package com.demo.simpleapi.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String processGreeting(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Input");
        }

        char firstChar = Character.toLowerCase(name.trim().charAt(0));
        if (firstChar < 'a' || firstChar > 'm') {
            throw new IllegalArgumentException("Invalid Input");
        }

        return "Hello " + name.trim();
    }
}
