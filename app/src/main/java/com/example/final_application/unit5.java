package com.example.final_application;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class unit5 extends AppCompatActivity implements View.OnClickListener {
    Button btnTest5;
    WebView web5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnTest5 = findViewById(R.id.btnTest5);
        btnTest5.setOnClickListener(this);
        web5 = findViewById(R.id.web5);
        web5.loadUrl("file:///android_asset/lessons/unit5.html");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTest5){
            Intent goTest5 = new Intent(unit5.this, TestUnit_5.class);
            startActivity(goTest5);
        }
    }
}