package com.jumayu.nptelmcq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Question> quesClass;
    static int score=0;
    Boolean submitted;
    Context mContext;
    Bundle getIndication;

    public MyAdapter(Context mContext, ArrayList<Question> quesClass, Boolean submitted) {

        this.quesClass=quesClass;
        this.mContext=mContext;
        this.submitted=submitted;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_format, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
//        position = AskQuestion.quesNo-1;
        holder.question.setText("Q" + (position+1) + ". " + quesClass.get(position).getQuestion());
        Log.d("question", "" + holder.question.getText());
        holder.r1.setText("a. " + quesClass.get(position).getOpt1());
        holder.r2.setText("b. " + quesClass.get(position).getOpt2());
        holder.r3.setText("c. " + quesClass.get(position).getOpt3());
        holder.r4.setText("d. " + quesClass.get(position).getOpt4());
        holder.correctAnsId = quesClass.get(position).getCorrOpt();

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(holder.radioGroup.getCheckedRadioButtonId()==holder.correctAnsId) {
                    score+=1;
                    holder.marks_statement.setText("Correct Answer!");
                    holder.marks_statement.setTextColor(Color.GREEN);
                }
                else {
                    holder.marks_statement.setText("Wrong Answer!");
                    holder.marks_statement.setTextColor(Color.RED);
                }
            }
        });

        if(submitted) {
            holder.marks_statement.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        Log.d("something", "" + AskQuestion.quesNo);
        return AskQuestion.quesNo;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioButton r1, r2, r3, r4;
        RadioGroup radioGroup;
        int correctAnsId=0;
        TextView marks_statement;
        RadioButton rbChecked;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            r1 = itemView.findViewById(R.id.opt1);
            r2 = itemView.findViewById(R.id.opt2);
            r3 = itemView.findViewById(R.id.opt3);
            r4 = itemView.findViewById(R.id.opt4);
            radioGroup = itemView.findViewById(R.id.radio_group);
            marks_statement = itemView.findViewById(R.id.marks_id);
        }
    }
}
