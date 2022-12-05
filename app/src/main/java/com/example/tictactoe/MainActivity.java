package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
    //    yellow = 0, red = 1
    int activePlayer = 0;

    boolean gameIsActive = true;
    //    unplaced = 2
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    final int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameIsActive) {

            gameState[tappedCounter]= activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }

            else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(100);

            for(int [] winningPosition : winningPositions)
            {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2)
                {
                    gameIsActive = false;
                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0)
                    {
                        winner = "Yellow";
                    }


//                    someone has won the game

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(String.format("%s has won!", winner));
                    LinearLayout linearlayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    linearlayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    boolean gameIsOver = true;
                    for(int counterState: gameState)
                    {
                        if(counterState == 2)
                        {
                            gameIsOver = false;
                        }
                    }

                    if (gameIsOver)
                    {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(String.format("It's a draw !"));
                        LinearLayout linearlayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        linearlayout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    public void playAgain(View view)
    {
        gameIsActive = true;
        LinearLayout linearlayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearlayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        Arrays.fill(gameState, 2);


        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i ++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}