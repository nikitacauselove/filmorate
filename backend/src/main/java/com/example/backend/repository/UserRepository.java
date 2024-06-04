package com.example.backend.repository;

import com.example.backend.entity.User;
import com.example.backend.service.impl.UserServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users where id in ((select receiving_user_id from friendship where requesting_user_id = :id)" +
            "intersect (select receiving_user_id from friendship where requesting_user_id = :otherUserId))", nativeQuery = true)
    List<User> findCommonFriends(@Param("id") Long id, @Param("otherUserId") Long otherUserId);


    @Query(value = UserServiceImpl.mostRelevantUsersSql, nativeQuery = true)
    List<Long> findAllForRecommendations(@Param("id") Long id);
}
