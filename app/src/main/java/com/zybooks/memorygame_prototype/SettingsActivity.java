package com.zybooks.memorygame_prototype;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings_container, new SettingsFragment())
                    .commit();
        }

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(v -> showResetConfirmationDialog());
    }
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            SwitchPreferenceCompat themePref = findPreference("dark_theme");
            if (themePref != null) {
                themePref.setOnPreferenceChangeListener((preference, newValue) -> {

                    // Turn on or off night mode
                    if ((Boolean) newValue) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                    return true;
                });
            }
        }
    }
    private void resetLevels() {
        SharedPreferences sharedPreferences = getSharedPreferences("LevelCompletion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(this, "Levels have been reset", Toast.LENGTH_SHORT).show();
    }

    private void showResetConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Reset App")
                .setMessage("Are you sure you want to reset the app? This will clear all saved progress.")
                .setPositiveButton("Yes", (dialog, which) -> resetLevels())
                .setNegativeButton("No", null) // Do nothing if "No" is clicked
                .show();
    }
}