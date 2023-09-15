package com.ex.business.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByEmail(String userEmail);
//    @Query("SELECT u FROM UserProfile u WHERE u.email = :userEmail")
//    UserProfile findByEmail(@Param("userEmail") String userEmail);

//    UserProfile findByEmailAndPassword(String userEmail, String userPassword);

}
