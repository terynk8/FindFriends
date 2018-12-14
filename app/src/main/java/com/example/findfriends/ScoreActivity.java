package com.example.findfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private TextView textViewDisplayScore;
    private Button buttonShareResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        wireWidgets();
        textViewDisplayScore.append(getIntent().getStringExtra(QuizActivity.EXTRA_SENT_PERSONALITY));

        buttonShareResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent with the desired action
                Intent intentShare = new Intent(Intent.ACTION_SEND);

                //set the data type of the stuff we're packaging away
                //can ook up the type you need on the internet
                intentShare.setType("text/plain");

                //put the extra with the message
                intentShare.putExtra(Intent.EXTRA_TEXT, textViewDisplayScore.getText().toString());

                //launch the activity
                startActivity(intentShare);

            }
        });
    }

    private void wireWidgets() {
        textViewDisplayScore = findViewById(R.id.textView_score_displayscore);
        buttonShareResults = findViewById(R.id.button_score_shareresults);
        //textViewDisplayScore.setText(getIntent().getStringExtra(QuizActivity.EXTRA_SCORE_MESSAGE));
    }
}
