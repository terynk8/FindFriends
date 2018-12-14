package com.example.findfriends;

import java.util.List;

public class Quiz {
        private List<Question> questions;
        private int score = 0;
        private int currentquestion = -1;
//ask here if there are more questions


        public Quiz(List<Question> questions) {
            this.questions = questions;
        }

        public Question getMoreQuestion() {
            currentquestion++;
            return questions.get(currentquestion);
        }

        public boolean hasMoreQuestions() {

            return currentquestion + 1 < questions.size();
        }

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Question getCurrentquestion() {
            return questions.get(currentquestion);
        }

        public void setCurrentquestion(int currentquestion) {
            this.currentquestion = currentquestion;
        }
    }
