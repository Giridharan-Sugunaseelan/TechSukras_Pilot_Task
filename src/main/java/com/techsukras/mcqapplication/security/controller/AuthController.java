package com.techsukras.mcqapplication.security.controller;

import com.techsukras.mcqapplication.dto.RegisterDto;
import com.techsukras.mcqapplication.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody RegisterDto dto){
        String status = this.authService.register(dto);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

}
