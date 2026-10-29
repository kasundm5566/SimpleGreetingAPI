package com.demo.simpleapi.controller;

import com.demo.simpleapi.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)

public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GreetingService greetingService;

    @Test
    void testValidInputs() throws Exception {
        Mockito.when(greetingService.processGreeting("alice")).thenReturn("Hello Alice");

        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Alice"))
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    void testInvalidInputs() throws Exception {
        Mockito.when(greetingService.processGreeting("nancy"))
                .thenThrow(new IllegalArgumentException("Invalid Input"));

        mockMvc.perform(get("/hello-world").param("name", "nancy"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"))
                .andExpect(jsonPath("$.message").doesNotExist());
    }

    @Test
    void testMissingInput() throws Exception {
        Mockito.when(greetingService.processGreeting(null))
                .thenThrow(new IllegalArgumentException("Invalid Input"));

        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }
}
