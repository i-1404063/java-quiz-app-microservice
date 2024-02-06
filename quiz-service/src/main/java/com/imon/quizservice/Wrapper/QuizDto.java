package com.imon.quizservice.Wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizDto {
    private String title;
    private String category;
    private Integer numQ;
}
