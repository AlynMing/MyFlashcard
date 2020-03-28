package com.example.yinfa.myflashcard;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if(allFlashcards != null && allFlashcards.size()>0){
            ((TextView)findViewById(R.id.answer)).setText(allFlashcards.get(0).getAnswer());
            ((TextView)findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
        }
        findViewById(R.id.Next).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);
                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });

                currentCardDisplayedIndex ++;
                if ( currentCardDisplayedIndex > allFlashcards.size()-1){
                    currentCardDisplayedIndex = 0;
                }
                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                ((TextView)findViewById(R.id.answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                ((TextView)findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                findViewById(R.id.flashcard_question).startAnimation(rightInAnim);

            }
        });

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                View answerSideView = findViewById(R.id.answer);

                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                float finalRadius = (float) Math.hypot(cx, cy);

                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(500);
                anim.start();
            }
        });



        findViewById(R.id.answer).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                findViewById(R.id.answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });

            findViewById(R.id.myButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                    startActivityForResult(intent, 450);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent2) {
        if (intent2 != null&& resultCode == RESULT_OK && requestCode == 450) {
            String q = intent2.getExtras().getString("question");
            String a = intent2.getExtras().getString("answer");
            flashcardDatabase.insertCard(new Flashcard(q,a));
            allFlashcards = flashcardDatabase.getAllCards();

        }
    }





    }

