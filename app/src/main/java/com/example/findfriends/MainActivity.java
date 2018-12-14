package com.example.findfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        private TextView textViewTitle;
        private EditText editTextmsg;
        private Button buttonStartQuiz;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            wireWidgets();

            buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //create an intent
                    //the arguments are where you are coming from (this) and where you are going (TargetActivity.class)
                    Intent intentSendMsg = new Intent(MainActivity.this, QuizActivity.class);

                    //start the new activity
                    startActivity(intentSendMsg);
                }
            });

        }

        private void wireWidgets() {
            textViewTitle = findViewById(R.id.textView_main_title);
            buttonStartQuiz = findViewById(R.id.button_main_startquiz);
        }
}
