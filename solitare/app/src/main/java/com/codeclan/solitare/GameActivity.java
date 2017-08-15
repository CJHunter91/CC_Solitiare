package com.codeclan.solitare;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
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

        LinearLayout gameStack1 = (LinearLayout) findViewById(R.id.stack_1);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

        for(Card card : game.getGameStacks().get(5)) {
            LinearLayout linearRow = new LinearLayout(this);
            linearRow.setOrientation(LinearLayout.VERTICAL);

                TextView cardView = new TextView(this);
                cardView.setText(card.getRank() + card.getSuit()+ ", ");
                gameStack1.addView(cardView);

        }
    }
}

