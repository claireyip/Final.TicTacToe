package com.example.finaltictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    private Button[][] buttons = new Button[3][3];
    /**
     * button state, determines which buttons have been set
     */
    private int[][] buttonState = new int[3][3];

    private boolean player1Turn = true;

    private int player1Scores = 0;
    private int player2Scores = 0;

    private TextView player1;
    private TextView player2;

    private int defaultColor = Color.parseColor("#C6C5C2");
    private int player1Color = Color.parseColor("#0DEBE4");
    private int player2Color = Color.parseColor("#EE9B2A");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1 = findViewById(R.id.text_view_p1);
        player2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                /**
                 * creates the name of the button to look up
                 */
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                System.out.println(resID);
                /**
                 * putting button in array
                 */
                buttons[i][j] = findViewById(resID);
                /**
                 * setting listener to the onClick function
                 */
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    /**
                     * calling buttonClicked function once button has been clicked
                     */
                    public void onClick(View view) {
                        buttonClicked(view);
                    }
                });
                /**
                 * setting color of button to default
                 */
                buttons[i][j].setBackgroundColor(defaultColor);
                /**
                 * setting buttonState to 0, which means players have not clicked on the button
                 */
                buttonState[i][j] = 0;
            }
        }

    }


    private void buttonClicked (View view) {
        int b = ((Button) view).getId();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                /**
                 * check to see if the button that has been clicked is at coordinates i and j
                 */
                if (buttons[i][j].getId() == b) {
                    x = i;
                    y = j;
                    break;
                }

            }
        }
        if (buttonState[x][y] != 0) {
            return;

        }
        if (player1Turn) {
            buttons[x][y].setBackgroundColor(player1Color);
            buttonState[x][y] = 1;
        } else {
            buttons[x][y].setBackgroundColor(player2Color);
            buttonState[x][y] = 2;
        }
        /**
         * switch players
         */
        if (player1Turn) {
            player1Turn = false;
        } else {
            player1Turn = true;
        }
        if(checkForWin() == 1) {
            player1Scores = player1Scores + 1;
            Toast toast = Toast.makeText(this, "Congratulations! Player 1 wins!",
                    Toast.LENGTH_LONG);
            toast.show();
            resetWin(buttons);
            // player 1 wins and reset
        } else if (checkForWin() == 2) {
            player2Scores = player2Scores + 1;
            Toast toast = Toast.makeText(this, "Congratulations! Player 2 wins!",
                    Toast.LENGTH_LONG);
            toast.show();
            resetWin(buttons);
            //player 2 win and reset
        }
    }
    private int checkForWin() {
        for (int i = 0; i < buttonState.length; i++) {
            if (0 != buttonState[i][0] && buttonState[i][0] == buttonState[i][1]
                    && buttonState[i][0] == buttonState[i][2]) {
                return buttonState[i][0];
            }
        }
        for (int j = 0; j < buttonState[0].length; j++) {
            if (0 != buttonState[0][j] && buttonState[0][j] == buttonState[1][j]
                    && buttonState[0][j] == buttonState[2][j]) {
                return buttonState[0][j];
            }
        }
        return 0;
    }
    private void resetWin(Button[][] buttons) {
        if (checkForWin() == 1 || checkForWin() == 2) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setBackgroundColor(defaultColor);
                }
            }
        }
    }
}

