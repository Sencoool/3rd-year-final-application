package com.example.final_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_sum_score_unit4 extends AppCompatActivity implements View.OnClickListener {
    Button btnRetest4;
    TextView scoreView4, textExcellent4, textGood4, textBad4;
    ImageButton imageExcellent4, imageGood4, imageBad4;
    String resultText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sum_score_unit4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRetest4 = findViewById(R.id.btnRetest4);
        scoreView4 = findViewById(R.id.scoreView4);
        textBad4 = findViewById(R.id.textBad4);
        textGood4 = findViewById(R.id.textGood4);
        textExcellent4 = findViewById(R.id.textExcellent4);
        imageExcellent4 = findViewById(R.id.imageExcellent4);
        imageGood4 = findViewById(R.id.imageGood4);
        imageBad4 = findViewById(R.id.imageBad4);
        btnRetest4.setOnClickListener(this);

        showScore();
    }

    private void showScore() {
        int finalScore = getIntent().getIntExtra("score",0);
        int totalQuestion = getIntent().getIntExtra("total",0);

        resultText = finalScore + "/" + totalQuestion;
        scoreView4.setText(resultText);
        if (finalScore > 9) {
            textExcellent4.setVisibility(View.VISIBLE);
            imageExcellent4.setVisibility(View.VISIBLE);
        } else if (finalScore >= 5) {
            textGood4.setVisibility(View.VISIBLE);
            imageGood4.setVisibility(View.VISIBLE);
        } else {
            textBad4.setVisibility(View.VISIBLE);
            imageBad4.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRetest4){
            Intent goTest4 = new Intent(activity_sum_score_unit4.this, TestUnit_4.class);
            startActivity(goTest4);
        }
    }
}