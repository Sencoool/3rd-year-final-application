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

public class activity_sum_score_unit5 extends AppCompatActivity implements View.OnClickListener {
    Button btnRetest5;
    TextView scoreView5, textExcellent5, textGood5, textBad5;
    ImageButton imageExcellent5, imageGood5, imageBad5;
    String resultText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sum_score_unit5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRetest5 = findViewById(R.id.btnRetest5);
        scoreView5 = findViewById(R.id.scoreView5);
        textBad5 = findViewById(R.id.textBad5);
        textGood5 = findViewById(R.id.textGood5);
        textExcellent5 = findViewById(R.id.textExcellent5);
        imageExcellent5 = findViewById(R.id.imageExcellent5);
        imageGood5 = findViewById(R.id.imageGood5);
        imageBad5 = findViewById(R.id.imageBad5);
        btnRetest5.setOnClickListener(this);

        showScore();
    }

    private void showScore() {
        int finalScore = getIntent().getIntExtra("score",0);
        int totalQuestion = getIntent().getIntExtra("total",0);

        resultText = finalScore + "/" + totalQuestion;
        scoreView5.setText(resultText);
        if (finalScore > 9) {
            textExcellent5.setVisibility(View.VISIBLE);
            imageExcellent5.setVisibility(View.VISIBLE);
        } else if (finalScore >= 5) {
            textGood5.setVisibility(View.VISIBLE);
            imageGood5.setVisibility(View.VISIBLE);
        } else {
            textBad5.setVisibility(View.VISIBLE);
            imageBad5.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRetest5){
            Intent goTest4 = new Intent(activity_sum_score_unit5.this, TestUnit_5.class);
            startActivity(goTest4);
        }
    }
}