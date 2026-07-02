package com.ramesh.studenterp.service;

import com.ramesh.studenterp.dto.request.LoginRequest;
import com.ramesh.studenterp.dto.request.RegisterRequest;
import com.ramesh.studenterp.dto.response.LoginResponse;
import com.ramesh.studenterp.dto.response.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

}
