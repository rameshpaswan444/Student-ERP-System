//package com.ramesh.studenterp.serviceImpl;
//
//import com.ramesh.studenterp.dto.request.LoginRequest;
//import com.ramesh.studenterp.dto.request.RegisterRequest;
//import com.ramesh.studenterp.dto.response.LoginResponse;
//import com.ramesh.studenterp.dto.response.UserResponse;
//import com.ramesh.studenterp.entity.Role;
//import com.ramesh.studenterp.entity.User;
//import com.ramesh.studenterp.exception.DuplicateResourceException;
//import com.ramesh.studenterp.mapper.UserMapper;
//import com.ramesh.studenterp.repository.RoleRepository;
//import com.ramesh.studenterp.repository.UserRepository;
//import com.ramesh.studenterp.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final UserMapper userMapper;
//
//    @Override
//    public UserResponse register(RegisterRequest request) {
//
//        if (userRepository.existsByEmail(request.getEmail())){
//            throw new DuplicateResourceException("Email already exists.")
//        }
//
//        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())){
//            throw new DuplicateResourceException("Phone number already exists.")
//        }
//
//        Role user = userMapper.toEntity(request);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        user.setRole(studentRole);
//
//        User savedUser = userRepository.save(user);
//        return userMapper.toResponse(savedUser) ;
//    }
//
//    @Override
//    public LoginResponse login(LoginRequest request) {
//        return null;
//    }
//}
