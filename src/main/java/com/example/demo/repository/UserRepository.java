package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            "" + "select case when count (s) > 0 then " +
                    "true else false end " +
                    "from User s " +
                    "where s.email =?1"
    )
    Boolean selectExistsEmail(String email);
    Boolean existsUserByEmail(String email);
    User  findUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}