package com.example.yinfa.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            ((TextView)findViewById(R.id.flashcard_question)).setText(q);
            ((TextView)findViewById(R.id.answer)).setText(a);


        }
    }





    }

