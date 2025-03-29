package com.example.final_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TestUnit_5 extends AppCompatActivity implements View.OnClickListener {
    Button btnNext5, btnSubmit5, btnBack5;
    TextView questionView5;
    RadioButton unit5c1, unit5c2, unit5c3, unit5c4;
    RadioGroup radioGroup5;

    int totalQuestion = QuestionManager.question5.length;
    int currentQuestionIndex = 0;
    int selectedAnswerIndex = -1; // default not select answer
    int finalScore = 0;
    int[] previousAnswers; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_unit5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        previousAnswers = new int[totalQuestion]; // adding size to previousAnswer
        for (int i = 0; i < totalQuestion; i++) {
            previousAnswers[i] = -1;
        }

        questionView5 = findViewById(R.id.questionView5);
        radioGroup5 = findViewById(R.id.radioGroup5);
        unit5c1 = findViewById(R.id.unit5c1);
        unit5c2 = findViewById(R.id.unit5c2);
        unit5c3 = findViewById(R.id.unit5c3);
        unit5c4 = findViewById(R.id.unit5c4);
        btnNext5 = findViewById(R.id.btnNext5);
        btnSubmit5 = findViewById(R.id.btnSubmit5);
        btnBack5 = findViewById(R.id.btnBack5);
        btnNext5.setOnClickListener(this);
        btnSubmit5.setOnClickListener(this);
        btnBack5.setOnClickListener(this);

        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.unit5c1) {
                    selectedAnswerIndex = 0;
                } else if (checkedId == R.id.unit5c2) {
                    selectedAnswerIndex = 1;
                } else if (checkedId == R.id.unit5c3) {
                    selectedAnswerIndex = 2;
                } else if (checkedId == R.id.unit5c4) {
                    selectedAnswerIndex = 3;
                }
            }
        });

        loadQuestion();
    }

    private void setButtonVisibility() {
        if (currentQuestionIndex >= 1) {
            btnBack5.setVisibility(View.VISIBLE);
        } else {
            btnBack5.setVisibility(View.INVISIBLE);
        }

        if (currentQuestionIndex < totalQuestion - 1) {
            // If 0-8 < 9 (not on the last question),
            btnNext5.setVisibility(View.VISIBLE);
            btnSubmit5.setVisibility(View.GONE);
        } else if (currentQuestionIndex == totalQuestion - 1) {
            // If 9 == 9 (on the last question)
            btnNext5.setVisibility(View.GONE);
            btnSubmit5.setVisibility(View.VISIBLE);
        }
    }

    public void loadQuestion(){
        questionView5.setText(QuestionManager.question5[currentQuestionIndex]);
        unit5c1.setText(QuestionManager.choices5[currentQuestionIndex][0]);
        unit5c2.setText(QuestionManager.choices5[currentQuestionIndex][1]);
        unit5c3.setText(QuestionManager.choices5[currentQuestionIndex][2]);
        unit5c4.setText(QuestionManager.choices5[currentQuestionIndex][3]);

        if (previousAnswers[currentQuestionIndex] != -1) {
            loadPrevAnswer();  // if already answer then load PrevAnswer
        } else {
            radioGroup5.clearCheck();
            selectedAnswerIndex = -1;
        }
    }

    public void loadPrevAnswer() {
        selectedAnswerIndex = previousAnswers[currentQuestionIndex];
        if (previousAnswers[currentQuestionIndex] == 0) unit5c1.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 1) unit5c2.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 2) unit5c3.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 3) unit5c4.setChecked(true);
    }

    public int checkAnswer() {
        int score = 0;
        for (int i = 0; i < totalQuestion; i++) {
            if (previousAnswers[i] == QuestionManager.answer5[i]) {
                score++; // if right score++
            }
        }
        return score;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnNext5){
            if (selectedAnswerIndex == -1){
                Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                return;
            }
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex; // store current question answer
            currentQuestionIndex++;
            loadQuestion();
            setButtonVisibility();
        } else if (view.getId() == R.id.btnBack5) {
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
            currentQuestionIndex--;
            loadQuestion();
            setButtonVisibility();
        } else if (view.getId() == R.id.btnSubmit5) {
            if (selectedAnswerIndex == -1){
                Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                return;
            }
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
            finalScore = checkAnswer();
            Intent intent = new Intent(TestUnit_5.this, activity_sum_score_unit5.class);
            intent.putExtra("score",finalScore);
            intent.putExtra("total",totalQuestion);
            startActivity(intent);
            finish();
        }
    }
}