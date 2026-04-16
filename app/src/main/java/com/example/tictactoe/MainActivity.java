package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    private int[] board = new int[9]; // 0=empty, 1=X, 2=O
    private int roundCount = 0;

    private TextView tvStatus;
    private TextView tvScoreX;
    private TextView tvScoreO;
    private Button btnReset;

    private int scoreX = 0;
    private int scoreO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus  = findViewById(R.id.tv_status);
        tvScoreX  = findViewById(R.id.tv_score_x);
        tvScoreO  = findViewById(R.id.tv_score_o);
        btnReset  = findViewById(R.id.btn_reset);

        GridLayout grid = findViewById(R.id.grid_board);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                final int row = i, col = j;
                buttons[i][j].setOnClickListener(v -> onCellClicked(row, col));
            }
        }

        btnReset.setOnClickListener(v -> resetGame());
        updateStatus();
    }

    private void onCellClicked(int row, int col) {
        int index = row * 3 + col;
        if (board[index] != 0) return;

        board[index] = playerXTurn ? 1 : 2;
        String symbol = playerXTurn ? "X" : "O";
        int color = playerXTurn
                ? ContextCompat.getColor(this, R.color.player_x)
                : ContextCompat.getColor(this, R.color.player_o);

        buttons[row][col].setText(symbol);
        buttons[row][col].setTextColor(color);
        buttons[row][col].setEnabled(false);
        roundCount++;

        if (checkWin()) {
            if (playerXTurn) { scoreX++; tvScoreX.setText(String.valueOf(scoreX)); }
            else             { scoreO++; tvScoreO.setText(String.valueOf(scoreO)); }
            tvStatus.setText(symbol + " Wins!");
            disableAllButtons();
        } else if (roundCount == 9) {
            tvStatus.setText("It's a Draw!");
        } else {
            playerXTurn = !playerXTurn;
            updateStatus();
        }
    }

    private boolean checkWin() {
        // Rows, columns, diagonals
        int[][] wins = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };
        int current = playerXTurn ? 1 : 2;
        for (int[] w : wins) {
            if (board[w[0]] == current && board[w[1]] == current && board[w[2]] == current)
                return true;
        }
        return false;
    }

    private void disableAllButtons() {
        for (Button[] row : buttons)
            for (Button b : row)
                b.setEnabled(false);
    }

    private void updateStatus() {
        String player = playerXTurn ? "X" : "O";
        tvStatus.setText("Player " + player + "'s Turn");
    }

    private void resetGame() {
        board = new int[9];
        roundCount = 0;
        playerXTurn = true;
        for (Button[] row : buttons) {
            for (Button b : row) {
                b.setText("");
                b.setEnabled(true);
            }
        }
        updateStatus();
    }
}