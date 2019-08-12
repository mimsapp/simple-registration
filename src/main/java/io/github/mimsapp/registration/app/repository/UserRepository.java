package io.github.mimsapp.registration.app.repository;

import io.github.mimsapp.registration.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT count(1) FROM User WHERE email = ?1")
    Integer countEmailAppearances(String email);

    @Query("SELECT count(1) FROM User WHERE mobile_number = ?1")
    Integer countMobileNumberAppearances(String mobileNumber);

}