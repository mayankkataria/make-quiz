package com.jumayu.nptelmcq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;

//public class MyViewModel extends ViewModel {
//    private MutableLiveData<List<User>> users;
//    public LiveData<List<User>> getUsers() {
//        if (users == null) {
//            users = new MutableLiveData<List<User>>();
//            loadUsers();
//        }
//        return users;
//    }
//
//    private void loadUsers() {
//        // Do an asynchronous operation to fetch users.
//    }
//}

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    TextView question;
    RadioGroup radioGroup;
    Button submitBtn;
    Button addQues;
    RecyclerView recyclerView;
    MyAdapter adapter;
    TextView resultText;
    static ArrayList<Question> quesClass;
    Question quesObj;
    DividerItemDecoration mDividerItemDecoration;
    LinearLayoutManager mLayoutManager;
    TextView marks_statement;
    Boolean submitted = false;


//    @Nullable
//    @Override
//    public ArrayList<Question> onRetainCustomNonConfigurationInstance() {
//        return quesClass;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        savedInstanceState.put("quesClass", ArrayList<Question>());
        setContentView(R.layout.recycler_view);

        findViews();

//        addExampleQues();
        if(adapter == null) {
            buildRecyclerView();
        }

        if(quesClass == null) {
            quesClass = new ArrayList<>();
        }

        if(AskQuestion.quesNo>0) {
            quesObj = (Question) getIntent().getSerializableExtra("quesObj");
            quesClass.add(quesObj);
            buildRecyclerView();
            adapter.notifyItemInserted(AskQuestion.quesNo-1);
        }


        addQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent askIntent = new Intent(MainActivity.this, AskQuestion.class);
                startActivity(askIntent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent sendScore = new Intent(MainActivity.this, MyAdapter.class);
//                sendScore.putExtra("submitted", true);
                adapter.submitted=true;
                recyclerView.setAdapter(adapter);
                mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        mLayoutManager.getOrientation());
                recyclerView.addItemDecoration(mDividerItemDecoration);
//                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.result, null);
//                view.showContextMenu();
//                resultText.setText(MyAdapter.score);
            }
        });
    }


    private void findViews() {
        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radio_group);
        submitBtn = findViewById(R.id.submit_button);
        addQues = findViewById(R.id.add_question_btn);
        resultText = findViewById(R.id.result_text);
        marks_statement = findViewById(R.id.marks_id);

    }

    private void addExampleQues() {
        quesClass = new ArrayList<>();
        String question = "Correct Answer is 4.";
        String opt1 = "1", opt2 = "2", opt3 = "3", opt4 = "4";
    }

    public void buildRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MyAdapter(this, quesClass, submitted);
        recyclerView.setAdapter(adapter);
        mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
    }
}
