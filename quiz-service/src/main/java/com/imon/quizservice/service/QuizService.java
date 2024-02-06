package com.imon.quizservice.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imon.quizservice.Wrapper.*;
import com.imon.quizservice.entity.Quiz;
import com.imon.quizservice.feign.QuizInterface;
import com.imon.quizservice.repository.QuizDao;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,
                numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Successfully created the quiz", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionIds = quiz.get().getQuestionIds();

        List<QuestionWrapper> questions = quizInterface.getQuestionsFromId(questionIds).getBody();

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {

        Integer score = quizInterface.getScore(response).getBody();
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
