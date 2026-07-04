package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.dto.request.LoginRequest;
import com.ramesh.studenterp.dto.request.RegisterRequest;
import com.ramesh.studenterp.dto.response.LoginResponse;
import com.ramesh.studenterp.dto.response.UserResponse;
import com.ramesh.studenterp.entity.Role;
import com.ramesh.studenterp.entity.User;
import com.ramesh.studenterp.enums.RoleType;
import com.ramesh.studenterp.exception.DuplicateResourceException;
import com.ramesh.studenterp.exception.ResourceNotFoundException;
import com.ramesh.studenterp.mapper.UserMapper;
import com.ramesh.studenterp.repository.RoleRepository;
import com.ramesh.studenterp.repository.UserRepository;
import com.ramesh.studenterp.security.CustomUserDetails;
import com.ramesh.studenterp.security.jwt.JwtService;
import com.ramesh.studenterp.service.EmailService;
import com.ramesh.studenterp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private  final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw  new DuplicateResourceException("Email already exists.");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new DuplicateResourceException("Phone number already exists.");
        }

        Role studentRole = roleRepository.findByName(RoleType.STUDENT)
                .orElseThrow(()-> new ResourceNotFoundException("Student role not found."));

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(studentRole);

        User savedUser = userRepository.save(user);
        System.out.println("Sending email to: " + savedUser.getEmail());

        emailService.sendWelcomeEmail(savedUser);

        System.out.println("Email sent successfully.");

        return userMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        CustomUserDetails userDetails = new CustomUserDetails(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().getName().name());
        String token = jwtService.generateToken(claims, userDetails);

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .build();
    }
}
