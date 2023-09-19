package com.ex.business.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByEmail(String userEmail);
    Optional<UserProfile> findById(Long id);
    UserProfile findByNameContains(String Keyword);

    @Query("SELECT u FROM UserProfile u WHERE u.age > :userAge ORDER BY u.age ASC")
    List<UserProfile> getAllUserProfileTheirAge(@Param("userAge") Byte age);

    @Query(
            value = "SELECT up FROM UserProfile up WHERE up.name LIKE :name AND up.age > :userAge ORDER BY up.age DESC"
    )
    List<UserProfile> findByNameLikeAndAgeAbove(
            @Param("name") String name, @Param("userAge") Byte age);
//    @Query("SELECT u FROM UserProfile u WHERE u.email = :userEmail")
//    UserProfile findByEmail(@Param("userEmail") String userEmail);
//    UserProfile findByEmailAndPassword(String userEmail, String userPassword);
}
