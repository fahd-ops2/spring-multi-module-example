package com.spring.client.controller;

import com.example.config.UserClient;
import com.example.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class ApiController {

    private final UserClient userClient;


    @GetMapping(value = "/all",produces = "text/markdown")
    public ResponseEntity<String> getAllUsers() {
        List<User> users = userClient.getUsers();

        // Convert List<User> to Markdown format
        StringBuilder markdown = new StringBuilder();
        markdown.append("| Username | Email |\n");
        markdown.append("|----------|-------|\n");

        for (User user : users) {
            markdown.append("| ").append(user.getUsername()).append(" | ").append(user.getEmail()).append(" |\n");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "text/markdown")
                .body(markdown.toString());
    }

    @GetMapping(value = "/test",produces = "text/markdown")
    public String test() {
        return "Test successful!";
    }
}

