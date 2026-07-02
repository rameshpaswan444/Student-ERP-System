//package com.ramesh.studenterp.repository;
//
//import com.ramesh.studenterp.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import java.util.Optional;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    Optional<User> findByEmail(String email);
//    Optional<User> findByPhoneNumber(String phoneNumber);
//    boolean existByEmail(String email);
//    boolean existsByPhoneNumber(String phoneNumber);
//
//
//}
package com.ramesh.studenterp.repository;

import com.ramesh.studenterp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

}