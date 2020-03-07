package com.example.yinfa.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
                currentCardDisplayedIndex ++;
                if ( currentCardDisplayedIndex > allFlashcards.size()-1){
                    currentCardDisplayedIndex = 0;
                }
                ((TextView)findViewById(R.id.answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                ((TextView)findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());


            }
        });

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                findViewById(R.id.answer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
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

