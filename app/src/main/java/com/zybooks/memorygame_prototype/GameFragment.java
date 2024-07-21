package com.zybooks.memorygame_prototype;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashSet;
import java.util.Set;

public class GameFragment extends Fragment {

    private static final String TAG = "GameFragment";

    private String level;
    private int levelNumber;
    private Set<Integer> pattern;
    private Set<Integer> userPattern;
    private GridLayout gameGrid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        if (getArguments() != null) {
            level = getArguments().getString("level");
            levelNumber = getArguments().getInt("level_number");
        }

        Log.d(TAG, "GameFragment onCreateView: Level: " + level + ", Level Number: " + levelNumber);

        pattern = new HashSet<>();
        userPattern = new HashSet<>();

        gameGrid = view.findViewById(R.id.game_grid);

        setupGrid(view);

        Button backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> getActivity().onBackPressed());

        Button submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> checkPattern());

        // Load pattern
        loadPattern(view);

        return view;
    }

    private void setupGrid(View view) {
        Log.d(TAG, "GameFragment setupGrid: Setting up grid.");
        int rowCount = 4;
        int columnCount = 4;

        if ("medium".equals(level)) {
            rowCount = 5;
            columnCount = 5;
        } else if ("hard".equals(level)) {
            rowCount = 6;
            columnCount = 6;
        }

        gameGrid.setRowCount(rowCount);
        gameGrid.setColumnCount(columnCount);

        for (int i = 0; i < rowCount * columnCount; i++) {
            Button gridItem = new Button(getContext());
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(4, 4, 4, 4); // Add margins to create spacing between blocks
            gridItem.setLayoutParams(params);
            gridItem.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            int finalI = i;
            gridItem.setOnClickListener(v -> toggleCell(finalI, gridItem));
            gameGrid.addView(gridItem);
        }

        Log.d(TAG, "GameFragment setupGrid: Grid setup completed with " + rowCount + " rows and " + columnCount + " columns.");
    }

    private void loadPattern(View view) {
        Log.d(TAG, "GameFragment loadPattern: Loading pattern.");
        // Temporary: Hardcode pattern for testing
        if ("easy".equals(level)) {
            pattern.add(0);
            pattern.add(1);
            pattern.add(4);
            pattern.add(5);
        } else if ("medium".equals(level)) {
            pattern.add(0);
            pattern.add(1);
            pattern.add(2);
            pattern.add(4);
            pattern.add(5);
            pattern.add(6);
        } else if ("hard".equals(level)) {
            pattern.add(0);
            pattern.add(1);
            pattern.add(2);
            pattern.add(3);
            pattern.add(4);
            pattern.add(5);
            pattern.add(6);
            pattern.add(7);
        }

        Log.d(TAG, "GameFragment loadPattern: Pattern loaded: " + pattern.toString());

        showPattern(view);
    }

    private void showPattern(View view) {
        Log.d(TAG, "GameFragment showPattern: Showing pattern.");
        for (Integer cellIndex : pattern) {
            Button gridItem = (Button) gameGrid.getChildAt(cellIndex);
            gridItem.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }

        // Hide the pattern after 3 seconds
        new Handler().postDelayed(() -> hidePattern(gameGrid), 3000);
    }

    private void hidePattern(GridLayout gameGrid) {
        Log.d(TAG, "GameFragment hidePattern: Hiding pattern.");
        for (int i = 0; i < gameGrid.getChildCount(); i++) {
            Button gridItem = (Button) gameGrid.getChildAt(i);
            gridItem.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }
    }

    private void toggleCell(int cellIndex, Button gridItem) {
        Log.d(TAG, "GameFragment toggleCell: Toggling cell " + cellIndex);
        if (userPattern.contains(cellIndex)) {
            userPattern.remove(cellIndex);
            gridItem.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        } else {
            userPattern.add(cellIndex);
            gridItem.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }
    }

    private void checkPattern() {
        Log.d(TAG, "GameFragment checkPattern: Checking pattern.");
        if (pattern.equals(userPattern)) {
            Toast.makeText(getContext(), "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Incorrect, try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
