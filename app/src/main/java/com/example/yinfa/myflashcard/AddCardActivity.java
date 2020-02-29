package com.example.yinfa.myflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                finish();
            }
        });

        findViewById(R.id.floatingActionButton2).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent2 = new Intent();
                String q = ((EditText)findViewById(R.id.editText)).getText().toString();
                String a = ((EditText)findViewById(R.id.editText2)).getText().toString();
                intent2.putExtra("question",q);
                intent2.putExtra("answer",a);
                setResult(RESULT_OK,intent2);
                finish();
            }
        });
    }
}
