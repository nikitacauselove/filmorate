package com.example.backend.repository;

import com.example.backend.entity.Film;
import com.example.backend.service.impl.UserServiceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {

    List<Film> findAllByDirectors_Id(Long directorId, Sort sort);

    @Query(value = UserServiceImpl.recommendedFilmsSql, nativeQuery = true)
    List<Film> findRecommendations(@Param("listOfUserId") Iterable<Long> listOfUserId, @Param("user_id") Long userId);


    @Query(value = "select * from films where id in (select film_id from film_likes where user_id = :userId intersect select film_id from film_likes where user_id = :friendId)", nativeQuery = true)
    List<Film> findCommon(Long userId, Long friendId);
}
