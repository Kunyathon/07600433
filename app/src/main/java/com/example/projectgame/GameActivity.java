package com.example.projectgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgame.db.AppDatabase;
import com.example.projectgame.model.User;
import com.example.projectgame.model.WordItem;
import com.example.projectgame.util.AppExecutors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mQuestionImageView;
    private Button[] mButtons = new Button[4];

    private String mAnswerWord;
    private Random mRandom;
    private List<WordItem> mItemList;

    private int score = 0;
    TextView scoreTextView;
    private int round = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionImageView = findViewById(R.id.question_image_view);
        mButtons[0] = findViewById(R.id.choice_1_button);
        mButtons[1] = findViewById(R.id.choice_2_button);
        mButtons[2] = findViewById(R.id.choice_3_button);
        mButtons[3] = findViewById(R.id.choice_4_button);

        mButtons[0].setOnClickListener(this);
        mButtons[1].setOnClickListener(this);
        mButtons[2].setOnClickListener(this);
        mButtons[3].setOnClickListener(this);


        mItemList = new ArrayList<>(Arrays.asList(WordListActivity.items));
        mRandom = new Random();
        newQuiz();

    }


    private void newQuiz() {


        // random word for game
        int answerIndex = mRandom.nextInt(mItemList.size());

        WordItem item = mItemList.get(answerIndex);
        // set image for test
        mQuestionImageView.setImageResource(item.imageResId);
        mAnswerWord = item.word;

        // random choice button answer
        int randomButton = mRandom.nextInt(4);
        mButtons[randomButton].setText(item.word);
        // pull answer item out form list
        mItemList.remove(item);
        // shuffle data
        Collections.shuffle(mItemList);

        for (int i = 0; i < 4; i++) {
            if (i == randomButton) {
                continue;
            }
            mButtons[i].setText(mItemList.get(i).word);
        }
    }


    private void checkRound() {
        round++;
        if (round == 5) {
            finish();
        }
        else{
            newQuiz();
        }
    }


    @Override
    public void onClick(View view) {
        Button b = findViewById(view.getId());
        String buttonText = b.getText().toString();


        if (mAnswerWord.equals(buttonText)) {
            score++;

                final User user = new User ("xx",0);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(GameActivity.this);
                        db.userDao().addUser(user);
                        finish();
                    }
                });

            checkRound();


        }
    }
}