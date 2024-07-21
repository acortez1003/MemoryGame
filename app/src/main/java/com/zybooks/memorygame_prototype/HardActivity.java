package com.zybooks.memorygame_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);

        Button level1Button = findViewById(R.id.level_1_button);
        level1Button.setOnClickListener(v -> startGameActivity(1));

        Button level2Button = findViewById(R.id.level_2_button);
        level2Button.setOnClickListener(v -> startGameActivity(2));

        Button level3Button = findViewById(R.id.level_3_button);
        level3Button.setOnClickListener(v -> startGameActivity(3));
    }

    private void startGameActivity(int levelNumber) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", "hard");
        intent.putExtra("level_number", levelNumber);
        startActivity(intent);
    }
}
