package com.imon.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imon.quizservice.entity.*;;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
