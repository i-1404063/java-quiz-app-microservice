package com.imon.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imon.questionservice.Wrapper.QuestionWrapper;
import com.imon.questionservice.Wrapper.Response;
import com.imon.questionservice.entity.Question;
import com.imon.questionservice.repository.QuestionDao;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionsByCategory(String cagtegory) {
        return questionDao.findByCategory(cagtegory);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQ) {
        List<Integer> questions = questionDao.getRandomQuestionsByCategory(category, numQ);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<Question> questions = questionDao.findAllById(questionIds);
        ArrayList<QuestionWrapper> response = new ArrayList();

        for (Question question : questions) {
            response.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(),
                    question.getOption2(), question.getOption3(), question.getOption4()));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        int right = 0;
        for (Response response : responses) {
            Question question = questionDao.getReferenceById(response.getId());
            if (response.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
