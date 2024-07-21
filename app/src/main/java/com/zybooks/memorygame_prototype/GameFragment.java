package com.zybooks.memorygame_prototype;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {

    private static final String TAG = "GameFragment";

    private String level;
    private int levelNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        level = getArguments().getString("level");
        levelNumber = getArguments().getInt("level_number");

        Log.d(TAG, "Level: " + level + ", Level Number: " + levelNumber);

        TextView levelTitle = view.findViewById(R.id.level_title);
        levelTitle.setText(level.substring(0, 1).toUpperCase() + level.substring(1) + " Level " + levelNumber);

        setupGrid(view);

        Button backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> getActivity().onBackPressed());

        Button submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            // Placeholder for submit action
            Log.d(TAG, "Submit clicked");
        });

        return view;
    }

    private void setupGrid(View view) {
        GridLayout gameGrid = view.findViewById(R.id.game_grid);
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
            gridItem.setLayoutParams(params);
            gameGrid.addView(gridItem);
        }
    }
}
