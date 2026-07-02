package com.ramesh.studenterp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Welcome Admin";
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public String teacher() {
        return "Welcome Teacher";
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public String student() {
        return "Welcome Student";
    }

    @GetMapping("/parent")
    @PreAuthorize("hasRole('PARENT')")
    public String parent() {
        return "Welcome Parent";
    }

    @GetMapping("/admin-teacher")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public String adminTeacher() {
        return "Accessible by Admin and Teacher";
    }
}
