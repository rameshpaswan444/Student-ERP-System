package com.ramesh.studenterp.config;

import com.ramesh.studenterp.entity.Role;
import com.ramesh.studenterp.enums.RoleType;
import com.ramesh.studenterp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;


    @Override
    public void run(String... args){

        createRole(RoleType.ADMIN, "System Administrator");
        createRole(RoleType.TEACHER, "Teacher");
        createRole(RoleType.STUDENT, "Student");
        createRole(RoleType.PARENT, "Parent");
    }

    private void createRole(RoleType roleType, String description){
        if (!roleRepository.existsByName(roleType)){
            Role role = Role.builder()
                    .name(roleType)
                    .description(description)
                    .build();

            roleRepository.save(role);

            System.out.println(roleType + "role created.");
        }
    }
}
