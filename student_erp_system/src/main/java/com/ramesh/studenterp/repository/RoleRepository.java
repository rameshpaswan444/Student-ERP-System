package com.ramesh.studenterp.repository;

import com.ramesh.studenterp.entity.Role;
import com.ramesh.studenterp.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByName(RoleType name);
    boolean existsByName(RoleType name);
}
