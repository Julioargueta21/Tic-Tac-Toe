/***
 *@Author Julio Argutea
 *Date: 2/26/2018
 */
package com.project.tictactoe;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Constructor
public class MainActivity extends AppCompatActivity {

    //Variables
    private int c[][];
    private int i, j, k = 0;
    private Boolean freeze = false;
    private Button b[][];
    private TextView name;
    private TextView whosTurn;
    private Button reset;
    private TextView result;
    private AI ai;

    //Methods
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //typecasting
        name = (TextView) findViewById(R.id.name);
        reset = (Button) findViewById(R.id.reset);

        //OnClickListener for reset button
        reset.setOnClickListener(new View.OnClickListener() {
            //Once player clicks the startButton, the board is set
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                setBoard();
                freeze = false;
            }
        });

        playerName();
        setBoard();
        setBoard();
    }


    //Displays the name of the player
    public void playerName() {
        name.setText("Player: " + getIntent().getExtras().getString("NAME"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Creates the board
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setBoard() {
        ai = new AI();
        b = new Button[4][4];
        c = new int[4][4];

        //typecasting
        result = (TextView) findViewById(R.id.result);
        whosTurn = (TextView) findViewById(R.id.whosTurn);

        b[1][3] = (Button) findViewById(R.id.one);
        b[1][2] = (Button) findViewById(R.id.two);
        b[1][1] = (Button) findViewById(R.id.three);

        b[2][3] = (Button) findViewById(R.id.four);
        b[2][2] = (Button) findViewById(R.id.five);
        b[2][1] = (Button) findViewById(R.id.six);

        b[3][3] = (Button) findViewById(R.id.seven);
        b[3][2] = (Button) findViewById(R.id.eight);
        b[3][1] = (Button) findViewById(R.id.nine);


        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }

        result.setText("");
        whosTurn.setText("");

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if (!b[i][j].isEnabled()) {
                    b[i][j].setBackground(getDrawable(R.drawable.blank));
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);

                }
            }
        }

    }

    //anonymous clickListener
    class MyClickListener implements View.OnClickListener {

        int x;
        int y;

        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View view) {
            if (b[x][y].isEnabled() && !freeze) {
                b[x][y].setEnabled(false);
                //This is where the X file is getting located will be used to mark where you click
                b[x][y].setBackground(getDrawable(R.drawable.letterx));

                c[x][y] = 0;
                result.setText("");
                whosTurn.setText("Its the AI's turn");

                if (!checkBoard()) {
                    ai.takeTurn();
                }
            }
        }
    }

    //AI is looking for optimal location to block a win
    private class AI {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void takeTurn() {
            if (c[1][1] == 2 &&
                    ((c[1][2] == 0 && c[1][3] == 0) ||
                            (c[2][2] == 0 && c[3][3] == 0) ||
                            (c[2][1] == 0 && c[3][1] == 0))) {
                markSquare(1, 1);
            } else if (c[1][2] == 2 &&
                    ((c[2][2] == 0 && c[3][2] == 0) ||
                            (c[1][1] == 0 && c[1][3] == 0))) {
                markSquare(1, 2);
            } else if (c[1][3] == 2 &&
                    ((c[1][1] == 0 && c[1][2] == 0) ||
                            (c[3][1] == 0 && c[2][2] == 0) ||
                            (c[2][3] == 0 && c[3][3] == 0))) {
                markSquare(1, 3);
            } else if (c[2][1] == 2 &&
                    ((c[2][2] == 0 && c[2][3] == 0) ||
                            (c[1][1] == 0 && c[3][1] == 0))) {
                markSquare(2, 1);
            } else if (c[2][2] == 2 &&
                    ((c[1][1] == 0 && c[3][3] == 0) ||
                            (c[1][2] == 0 && c[3][2] == 0) ||
                            (c[3][1] == 0 && c[1][3] == 0) ||
                            (c[2][1] == 0 && c[2][3] == 0))) {
                markSquare(2, 2);
            } else if (c[2][3] == 2 &&
                    ((c[2][1] == 0 && c[2][2] == 0) ||
                            (c[1][3] == 0 && c[3][3] == 0))) {
                markSquare(2, 3);
            } else if (c[3][1] == 2 &&
                    ((c[1][1] == 0 && c[2][1] == 0) ||
                            (c[3][2] == 0 && c[3][3] == 0) ||
                            (c[2][2] == 0 && c[1][3] == 0))) {
                markSquare(3, 1);
            } else if (c[3][2] == 2 &&
                    ((c[1][2] == 0 && c[2][2] == 0) ||
                            (c[3][1] == 0 && c[3][3] == 0))) {
                markSquare(3, 2);
            } else if (c[3][3] == 2 &&
                    ((c[1][1] == 0 && c[2][2] == 0) ||
                            (c[1][3] == 0 && c[2][3] == 0) ||
                            (c[3][1] == 0 && c[3][2] == 0))) {
                markSquare(3, 3);
            } else {

                //if AI cant block a move, it moves randomly
                Random rand = new Random();

                int a = rand.nextInt(4);
                int b = rand.nextInt(4);
                while (a == 0 || b == 0 || c[a][b] != 2) {
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
                }
                markSquare(a, b);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void markSquare(int x, int y) {
            if (!freeze) {
                b[x][y].setEnabled(false);
                //This is where the O file is getting located will be used to mark where you click
                b[x][y].setBackground(getDrawable(R.drawable.lettero));
                c[x][y] = 1;
                whosTurn.setText("Its your turn");
                checkBoard();
            }
        }
    }

    //Checks to see if the board has any winning combinations after every move
    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {

            //Changes the results to show winner then freezes the game
            result.setText("You win! :)");
            whosTurn.setText("Play Again!");
            gameOver = true;
            freeze = true;

        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {

            //Changed the text to show winner then freezes the game
            result.setText("You lost! :(");
            whosTurn.setText("Play Again!");
            gameOver = true;
            freeze = true;

        } else {
            boolean empty = false;
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    if (c[i][j] == 2) {
                        empty = true;
                    }
                }
            }
            if (!empty) {
                gameOver = true;
                freeze = true;
                result.setText("It's a tie");
                whosTurn.setText("Play Again!");
            }
        }
        return gameOver;
    }
}