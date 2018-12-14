package com.example.findfriends;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<Answer> answers = new ArrayList<Answer>();

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }
}