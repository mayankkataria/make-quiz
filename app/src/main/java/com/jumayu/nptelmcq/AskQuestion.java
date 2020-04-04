package com.jumayu.nptelmcq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class AskQuestion extends AppCompatActivity{

    private static final String TAG = "AskQuestion";
    EditText askQues;
    EditText askEt1;
    EditText askEt2;
    EditText askEt3;
    EditText askEt4;
    Button setBtn;
    Button cancelBtn;
    RadioGroup noTextRadioGroup;
    Question quesObj;
    static int quesNo=0;


    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        askQues = findViewById(R.id.ask_ques);
        setBtn = findViewById(R.id.set_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        noTextRadioGroup = findViewById(R.id.no_text_radio_group);
        askEt1 = findViewById(R.id.et1);
        askEt2 = findViewById(R.id.et2);
        askEt3 = findViewById(R.id.et3);
        askEt4 = findViewById(R.id.et4);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(askQues)) {
                    Toast.makeText(AskQuestion.this, "Please fill the question field.", Toast.LENGTH_SHORT).show();
                }
                else if(isEmpty(askEt1)) {
                    Toast.makeText(AskQuestion.this, "Please fill 1st option", Toast.LENGTH_SHORT).show();
                }
                else if(isEmpty(askEt2)) {
                    Toast.makeText(AskQuestion.this, "Please fill 2nd option", Toast.LENGTH_SHORT).show();
                }
                else if(isEmpty(askEt3)) {
                    Toast.makeText(AskQuestion.this, "Please fill 3rd option", Toast.LENGTH_SHORT).show();
                }
                else if(isEmpty(askEt4)) {
                    Toast.makeText(AskQuestion.this, "Please fill 4th option", Toast.LENGTH_SHORT).show();
                }
                else if(noTextRadioGroup.getCheckedRadioButtonId()==-1) {
                    Toast.makeText(AskQuestion.this, "Please mark the correct answer.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "radio id : " + noTextRadioGroup.getCheckedRadioButtonId());
                }
                else {
                    quesNo++;
                    quesObj = new Question();
                    quesObj.setQuestion(askQues.getText().toString());
                    quesObj.setOpt1(askEt1.getText().toString());
                    quesObj.setOpt2(askEt2.getText().toString());
                    quesObj.setOpt3(askEt3.getText().toString());
                    quesObj.setOpt4(askEt4.getText().toString());
                    quesObj.setCorrOpt(noTextRadioGroup.getCheckedRadioButtonId());

                    Intent backIntent = new Intent(AskQuestion.this, MainActivity.class);
                    backIntent.putExtra("quesObj", quesObj);
                    startActivity(backIntent);

                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(AskQuestion.this, MainActivity.class);
                startActivity(backIntent);
            }
        });
    }
}
