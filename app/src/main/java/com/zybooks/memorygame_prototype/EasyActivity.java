package com.zybooks.memorygame_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EasyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        Button level1Button = findViewById(R.id.level_1_button);
        level1Button.setOnClickListener(v -> startGameActivity(1));

        Button level2Button = findViewById(R.id.level_2_button);
        level2Button.setOnClickListener(v -> startGameActivity(2));

        Button level3Button = findViewById(R.id.level_3_button);
        level3Button.setOnClickListener(v -> startGameActivity(3));
    }

    private void startGameActivity(int levelNumber) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", "easy");
        intent.putExtra("level_number", levelNumber);
        startActivity(intent);
    }
}
