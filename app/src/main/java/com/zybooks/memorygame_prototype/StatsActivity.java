package com.zybooks.memorygame_prototype;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}
