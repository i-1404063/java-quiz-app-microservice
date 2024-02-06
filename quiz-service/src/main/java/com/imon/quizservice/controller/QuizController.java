package com.imon.quizservice.controller;

import java.util.*;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imon.quizservice.Wrapper.*;
import com.imon.quizservice.Wrapper.QuizDto;
import com.imon.quizservice.service.*;

@RestController
@RequestMapping(path = "quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping(path = "create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto) {
        return quizService.createQuiz(quizdto.getCategory(), quizdto.getNumQ(),
                quizdto.getTitle());
    }

    @GetMapping(path = "welcome")
    public String greeting() {
        return "Hello, user";
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
            @RequestBody List<Response> response) {
        return quizService.calculateResult(id, response);
    }
}
