package com.ex.business.users.Repositories;

import com.ex.business.users.Entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByNameAndEmail(String userEmail, String Email);
    Optional<UserProfile> findById(Long id);
    List<UserProfile> findAllByNameContains(String Keyword);

    @Query("SELECT u FROM UserProfile u WHERE u.age > :userAge ORDER BY u.age ASC")
    List<UserProfile> getAllUserProfileTheirAge(@Param("userAge") Byte age);

    @Query(
            value = "SELECT up FROM UserProfile up WHERE up.name LIKE :name% AND up.age > :userAge ORDER BY up.age DESC"
    )
    List<UserProfile> findByNameLikeAndAgeAbove(
            @Param("name") String name, @Param("userAge") Byte age);

//    this is a native query
    @Query(value = "select * from user_profile where name=:name", nativeQuery = true)
    List<UserProfile> getAllUserProfilesWhereNameIsEqualTo(String name);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_profile (name, age, email, password) VALUES (:name, :age, :email, :password)", nativeQuery = true)
    void addUserProfile(@Param("name") String name, @Param("age") Byte age, @Param("email") String email, @Param("password") String password);

    UserProfile findByName(String username);
//    @Query("SELECT u FROM UserProfile u WHERE u.email = :userEmail")
//    UserProfile findByEmail(@Param("userEmail") String userEmail);
//    UserProfile findByEmailAndPassword(String userEmail, String userPassword);
}
