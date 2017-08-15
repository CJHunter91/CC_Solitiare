package com.codeclan.solitare;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameLogic game = new GameLogic();
        game.newGame();

        LinearLayout gameStack1 = (LinearLayout) findViewById(R.id.game_stacks);
        gameStack1.setOrientation(LinearLayout.HORIZONTAL);

        int count = 0;
        for(ArrayList<Card> stack : game.getGameStacks()) {
            LinearLayout linearRow = new LinearLayout(this);
            linearRow.setOrientation(LinearLayout.VERTICAL);

            for (Card card : stack) {
                LinearLayout linearColumn = new LinearLayout(this);
                linearColumn.setOrientation(LinearLayout.HORIZONTAL);

                Button cardButton = new Button(this);
                cardButton.setText(card.getRank() + card.getSuit());
                cardButton.setId(count);
                cardButton.setTag(count, card);
                //make the last items height larger
                if(stack.indexOf(card) == stack.size()-1) {
                    cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(150, 200));
                }
                else{
                    cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(150, 100));
                }
                cardButton.setTextSize(10);
                linearColumn.addView(cardButton);
                linearRow.addView(linearColumn);
                count++;
            }
            gameStack1.addView(linearRow);
        }
    }
}

