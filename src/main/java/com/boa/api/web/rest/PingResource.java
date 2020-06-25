package com.boa.api.web.rest;

import java.time.Instant;

import com.boa.api.response.GenericResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingResource {
    
    @GetMapping("/ping")
    public ResponseEntity<GenericResponse> ping() {
        GenericResponse response = new GenericResponse();
        response.setCode("200");
        response.description("Service up!");
        response.dateResponse(Instant.now());
        return ResponseEntity.status(200).body(response);

    }
}