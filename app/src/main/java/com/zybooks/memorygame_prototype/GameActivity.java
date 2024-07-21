package com.zybooks.memorygame_prototype;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "GameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String level = getIntent().getStringExtra("level");
        int levelNumber = getIntent().getIntExtra("level_number", 1);

        Log.d(TAG, "GameActivity onCreate: Level: " + level + ", Level Number: " + levelNumber);

        TextView levelTitle = findViewById(R.id.level_title);
        levelTitle.setText(level.substring(0, 1).toUpperCase() + level.substring(1) + " Level " + levelNumber);

        // Create fragment and pass data
        GameFragment gameFragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString("level", level);
        args.putInt("level_number", levelNumber);
        gameFragment.setArguments(args);

        // Load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, gameFragment);
        transaction.commit();

        Log.d(TAG, "GameActivity Fragment transaction committed.");
    }
}
