package com.zybooks.memorygame_prototype;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String level = getIntent().getStringExtra("level");
        TextView levelTitle = findViewById(R.id.level_title);
        levelTitle.setText(level.substring(0, 1).toUpperCase() + level.substring(1));

        findViewById(R.id.start_game).setOnClickListener(v -> {
            // Start game logic (placeholder for now)
            Toast.makeText(this, "Starting " + level + " level game", Toast.LENGTH_SHORT).show();
        });
    }
}
