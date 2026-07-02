package com.ramesh.studenterp.dto.response;

import com.ramesh.studenterp.enums.RoleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String firstName;

    private String middleName; // optional

    private String lastName;

    private String email;

    private String phoneNumber;

    private RoleType role;

    private Boolean enabled;
}
