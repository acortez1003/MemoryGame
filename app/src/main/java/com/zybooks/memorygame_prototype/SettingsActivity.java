package com.zybooks.memorygame_prototype;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private EditText userInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button backButton = findViewById(R.id.back_button);
        userInput = findViewById(R.id.user_input);
        Button validateButton = findViewById(R.id.validate_button);

        backButton.setOnClickListener(v -> finish());

        validateButton.setOnClickListener(v -> validateInput());
    }

    private void validateInput() {
        String input = userInput.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (input.length() < 3) {
            Toast.makeText(this, "Input must be at least 3 characters", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Input is valid", Toast.LENGTH_SHORT).show();
        }
    }
}
