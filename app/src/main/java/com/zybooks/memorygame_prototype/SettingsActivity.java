package com.zybooks.memorygame_prototype;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "AppPrefs";
    private static final String PREF_DARK_MODE = "dark_mode";

    private Switch darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        darkModeSwitch = findViewById(R.id.dark_mode_switch);
        Button backButton = findViewById(R.id.back_button);

        //Loads the saved theme preference
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean(PREF_DARK_MODE, false);
        darkModeSwitch.setChecked(isDarkMode);
        updateTheme(isDarkMode);

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //Saves the user's theme choice
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(PREF_DARK_MODE, isChecked);
            editor.apply();

            //Applies the new theme
            updateTheme(isChecked);
            Toast.makeText(this, "Theme changed. Restart the app to see the effect.", Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void updateTheme(boolean isDarkMode) {
        AppCompatDelegate.setDefaultNightMode(isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
