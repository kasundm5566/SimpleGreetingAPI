package com.demo.simpleapi.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {

    private final GreetingService greetingService = new GreetingService();

    @Test
    void testNameStartsWithAtoM() {
        String result1 = greetingService.processGreeting("alice");
        assertEquals("Hello alice", result1);

        String result2 = greetingService.processGreeting("Bob");
        assertEquals("Hello Bob", result2);

        String result3 = greetingService.processGreeting(" Alice ");
        assertEquals("Hello Alice", result3);
    }

    @Test
    void testNameStartsWithNtoZ() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            greetingService.processGreeting("nancy");
        });
        assertEquals("Invalid Input", exception.getMessage());
    }

    @Test
    void testNameIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> greetingService.processGreeting(null));

        assertThrows(IllegalArgumentException.class, () -> greetingService.processGreeting("   "));
    }
}
