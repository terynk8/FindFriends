package com.example.findfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private Quiz quiz;
    private TextView textviewquestion;
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;
    private int score=0;
    public static String personality;
    public static final String EXTRA_SENT_MESSAGE = "the message";
    public static final String EXTRA_SENT_SCORE = "the score";
    public static final String TAG = "QuizActivity";
    public static final String EXTRA_SENT_PERSONALITY = "personality";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initializeQuiz();
        wireWidgets();
        setListeners();
        displayNextQuestion();

    }

    private String getPersonality() {
        if(score >=0 && score<=10) {
            personality = "INF";
        }
        else if(score >=11 && score <=20) {
            personality = "INT";
        }
        else if(score >=21 && score <=30) {
            personality = "ISF";
        }
        else if(score >=31 && score <=40) {
            personality = "IST";
        }
        else if(score >=41 && score <=50) {
            personality = "ENF";
        }
        else if(score >=51 && score <=60) {
            personality = "ENT";
        }
        else if(score >=61 && score <=70) {
            personality = "ESF";
        }
        else if(score >=71 && score <=80){
            personality = "EST";
        }
        return personality;
    }


    private void wireWidgets() {
        textviewquestion = findViewById(R.id.textView_quiz_questions);
        buttonA = findViewById(R.id.button_quiz_buttona);
        buttonB = findViewById(R.id.button_quiz_buttonb);
        buttonC = findViewById(R.id.button_quiz_buttonc);
        buttonD = findViewById(R.id.button_quiz_buttond);
        buttonA.setText(quiz.getCurrentquestion().getAnswers().get(0).getText());
        buttonB.setText(quiz.getCurrentquestion().getAnswers().get(1).getText());
        buttonC.setText(quiz.getCurrentquestion().getAnswers().get(2).getText());
        buttonD.setText(quiz.getCurrentquestion().getAnswers().get(3).getText());
    }


    private void initializeQuiz() {
        //call readFile method on the resource you get
        String jsonString = readTextFile(getResources().openRawResource(R.raw.questions));
        //create a gson object
        Gson gson = new Gson();
        //read your json file into an array of questions
        Question[] questions = gson.fromJson(jsonString, Question[].class);
        //convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        //verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());

        quiz = new Quiz(questionList);
        quiz.getMoreQuestion();
    }

    private void setListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = -1;
                switch (v.getId()) {
                    case R.id.button_quiz_buttona:
                        index = 0;
                        break;
                    case R.id.button_quiz_buttonb:
                        index = 1;
                        break;
                    case R.id.button_quiz_buttonc:
                        index = 2;
                        break;
                    case R.id.button_quiz_buttond:
                        index = 3;
                        break;
                }
                score += quiz.getCurrentquestion().getAnswers().get(index).getValue();
                displayNextQuestion();
            }
        };
        buttonA.setOnClickListener(listener);
        buttonB.setOnClickListener(listener);
        buttonC.setOnClickListener(listener);
        buttonD.setOnClickListener(listener);

        /*buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // quiz.getValue();

                displayNextQuestion();
                //Toast.makeText(QuizActivity.this, quiz.getCurrentquestion().getAnswers().get(0).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // quiz.getValue();
                String buttonText = buttonB.getText().toString();
                displayNextQuestion();
                //Toast.makeText(QuizActivity.this, buttonText, Toast.LENGTH_SHORT).show();
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // quiz.getValue();
                String buttonText = buttonC.getText().toString();
                displayNextQuestion();
               // Toast.makeText(QuizActivity.this, buttonText, Toast.LENGTH_SHORT).show();
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quiz.getValue();
                String buttonText = buttonD.getText().toString();
                displayNextQuestion();
                //Toast.makeText(QuizActivity.this, buttonText, Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    private void displayNextQuestion() {
        Toast.makeText(this, String.valueOf(score), Toast.LENGTH_SHORT).show();
        if (quiz.hasMoreQuestions()) {
            Question question = quiz.getMoreQuestion();
            textviewquestion.setText(question.getQuestion());
            List<Answer> answers = question.getAnswers();
            buttonA.setText(answers.get(0).getText());
            buttonB.setText(answers.get(1).getText());
            buttonC.setText(answers.get(2).getText());
            buttonD.setText(answers.get(3).getText());

        }
        else {
            Intent intentSendScore = new Intent(com.example.findfriends.QuizActivity.this, ScoreActivity.class);
            //intentSendScore.setType("text/Plain");
            intentSendScore.putExtra(EXTRA_SENT_MESSAGE, String.valueOf(quiz.getScore()));
            intentSendScore.putExtra(EXTRA_SENT_SCORE, score);
            intentSendScore.putExtra(EXTRA_SENT_PERSONALITY, getPersonality());
            startActivity(intentSendScore);
            //textView
        }
    }
    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

};
