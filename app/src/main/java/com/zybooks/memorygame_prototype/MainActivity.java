package com.zybooks.memorygame_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button easyButton = findViewById(R.id.easyButton);
        easyButton.setOnClickListener(v -> {
            startActivity(new Intent(this, EasyActivity.class));
        });

        Button mediumButton = findViewById(R.id.mediumButton);
        mediumButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MediumActivity.class));
        });

        Button hardButton = findViewById(R.id.hardButton);
        hardButton.setOnClickListener(v -> {
            startActivity(new Intent(this, HardActivity.class));
        });

        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });
    }
}
