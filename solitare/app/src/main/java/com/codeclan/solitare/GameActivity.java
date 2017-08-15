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

        ArrayList<TextView> cardViews = new ArrayList<>();

        RelativeLayout gameLayout = (RelativeLayout) findViewById(R.id.game_layout);
        int count = 0;


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        Button btn = new Button(this);
        btn.setText("real");
        btn.setBackgroundColor(Color.rgb(70, 80, 90));
        btn.setHeight(70);
        btn.setWidth(50);
        gameLayout.addView(btn);
//        for(ArrayList<Card> stack : game.getGameStacks()){
//            for(Card card : stack){
//                LinearLayout linearLayout = new LinearLayout(this);
//                TextView stackTextView = new TextView(this);
//                stackTextView.setText(card.getRank());
//                linearLayout.addView(stackTextView);
//                mainLayout.addView(linearLayout);
//
//            }
//        }
        }
    }

