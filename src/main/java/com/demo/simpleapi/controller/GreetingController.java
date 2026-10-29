package com.demo.simpleapi.controller;

import com.demo.simpleapi.model.ErrorResponse;
import com.demo.simpleapi.model.GreetingResponse;
import com.demo.simpleapi.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<Object> greet(@RequestParam(value = "name", required = false) String name) {
        try {
            String greetingResponse = greetingService.processGreeting(name);
            return ResponseEntity.ok(new GreetingResponse(greetingResponse));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        }
    }
}
