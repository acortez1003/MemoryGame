package com.zybooks.memorygame_prototype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EasyActivity extends AppCompatActivity {
    private Button level1Button, level2Button, level3Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        level1Button = findViewById(R.id.easy_lvl_1);
        level1Button.setOnClickListener(v -> startGameActivity(1));

        level2Button = findViewById(R.id.easy_lvl_2);
        level2Button.setOnClickListener(v -> startGameActivity(2));

        level3Button = findViewById(R.id.easy_lvl_3);
        level3Button.setOnClickListener(v -> startGameActivity(3));

        updateButtonColors();
    }

    private void startGameActivity(int levelNumber) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", "easy");
        intent.putExtra("level_number", levelNumber);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtonColors();  // Refresh the button colors when the activity resumes
    }

    private void updateButtonColors() {
        SharedPreferences sharedPreferences = getSharedPreferences("LevelCompletion", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("easy_level_1_completed", false)) {
            level1Button.setBackgroundColor(Color.GREEN);
        }
        if (sharedPreferences.getBoolean("easy_level_2_completed", false)) {
            level2Button.setBackgroundColor(Color.GREEN);
        }
        if (sharedPreferences.getBoolean("easy_level_3_completed", false)) {
            level3Button.setBackgroundColor(Color.GREEN);
        }
    }
}