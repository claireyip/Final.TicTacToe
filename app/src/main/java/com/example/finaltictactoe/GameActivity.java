package com.example.finaltictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    ButtonState[][] board = new ButtonState[3][3];
    boolean player1Turn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
    enum ButtonState {
        OCCUPIED,
        PLAYER1,
        PLAYER2
    }
}
