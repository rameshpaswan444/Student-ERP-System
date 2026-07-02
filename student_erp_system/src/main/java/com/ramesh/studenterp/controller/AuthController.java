package com.ramesh.studenterp.controller;

import com.ramesh.studenterp.dto.request.LoginRequest;
import com.ramesh.studenterp.dto.request.RegisterRequest;
import com.ramesh.studenterp.dto.response.LoginResponse;
import com.ramesh.studenterp.dto.response.UserResponse;
import com.ramesh.studenterp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){

        UserResponse response = userService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){

        LoginResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }

}
