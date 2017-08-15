package com.codeclan.solitare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameActivity extends AppCompatActivity {

    HashMap<Integer, Card> gameState;
    boolean isSelected;
    int selectedCard;
    GameLogic game;
    LinearLayout gameStack1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameState = new HashMap<>();
        isSelected = false;
        game = new GameLogic();
        game.newGame();


        //find the gameStacks view
        gameStack1 = (LinearLayout) findViewById(R.id.game_stacks);
        gameStack1.setOrientation(LinearLayout.HORIZONTAL);

        reDrawState(game, gameStack1);


        Button deckCard = (Button) findViewById(R.id.draw);
        deckCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.deckDraw();
                reDrawState(game, gameStack1);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.new_game) {
            game.newGame();
            reDrawState(game, gameStack1);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void reDrawState(GameLogic game, final LinearLayout gameStacks){
        final GameLogic redrawGame = game;
        gameStacks.removeAllViews();
        int count = 0;
        //create view for pile card
        final Button pileCard = (Button) findViewById(R.id.pile);
        if(game.getPile().size() > 0) {
            final Card pileCardObject = redrawGame.getPileCard();
            pileCard.setText(pileCardObject.getRank() + pileCardObject.getSuit());
            pileCard.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.i("TAG", "index :" + "Pile");
                    Toast.makeText(getApplicationContext(),
                            "Clicked Button Rank :" + "Pile",
                            Toast.LENGTH_SHORT).show();
                    redrawGame.moveToAce(pileCardObject);
                    reDrawState(redrawGame, gameStacks);
                    return true;
                }
            });
            pileCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Log.i("TAG", "index :" + pileCard.getId());
                        Toast.makeText(getApplicationContext(),
                                "Selected Button Rank :" + pileCard.getId(),
                                Toast.LENGTH_SHORT).show();
                        selectedCard = pileCard.getId();
                        isSelected = true;
                    }
            });
        }
        else{
            pileCard.setText("");
        }
        // find ace stacks
        ArrayList<Button> aceButtons = new ArrayList<>();
        Button aceStack1 = (Button) findViewById(R.id.ace_stack1);
        Button aceStack2 = (Button) findViewById(R.id.ace_stack2);
        Button aceStack3 = (Button) findViewById(R.id.ace_stack3);
        Button aceStack4 = (Button) findViewById(R.id.ace_stack4);
        aceButtons.add(aceStack1);
        aceButtons.add(aceStack2);
        aceButtons.add(aceStack3);
        aceButtons.add(aceStack4);
        //setAce stack to equal last card in stack
        count = 0;
        for(Button button : aceButtons){
            if(game.getAceStacks().get(count).size() > 0){
                String rank = game.getLastAceCard(count).getRank();
                String suit = game.getLastAceCard(count).getSuit();
                button.setText(rank + suit);
            }
            count++;
        }

        //go through the gameStacks to display cards
        for(final ArrayList<Card> stack : game.getGameStacks()) {
            LinearLayout linearRow = new LinearLayout(this);
            linearRow.setOrientation(LinearLayout.VERTICAL);
            //need to create condition that if stack == 0 create one empty button
            if(stack.size() == 0){
                Button cardButton = new Button(this);
                cardButton.setText("");
                cardButton.setId(count);
            }
            for (final Card card : stack) {
                //update hash
                gameState.put(count, card);
                LinearLayout linearColumn = new LinearLayout(this);
                linearColumn.setOrientation(LinearLayout.HORIZONTAL);

                Button cardButton = new Button(this);
                cardButton.setText(card.getRank() + card.getSuit());
                cardButton.setId(count);
                cardButton.setTextSize(10);
                //make the last items height larger
                if(stack.indexOf(card) == stack.size()-1) {
                    cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(150, 200));
                }
                else{
                    cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(150, 100));
                }
                linearColumn.addView(cardButton);
                linearRow.addView(linearColumn);
                final int index = count;
                cardButton.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Log.i("TAG", "index :" + index);
                        Toast.makeText(getApplicationContext(),
                                "Clicked Button Rank :" + index,
                                Toast.LENGTH_SHORT).show();
                        redrawGame.moveToAce(card);
                        reDrawState(redrawGame, gameStacks);
                        return true;
                    }
                });
                cardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(isSelected){
                            Log.i("TAG", "index :" + index);
                            Toast.makeText(getApplicationContext(),
                                    "Clicked Button Rank :" + index,
                                    Toast.LENGTH_SHORT).show();
                            if (selectedCard == pileCard.getId()){
                                redrawGame.movePileCard(redrawGame.getGameStacks().indexOf(stack));
                            }
                            else {
                                redrawGame.makeValidMove(gameState.get(selectedCard), redrawGame.getGameStacks().indexOf(stack));
                            }
                            reDrawState(redrawGame, gameStacks);
                            isSelected = false;
                        }
                        else{
                            Log.i("TAG", "index :" + index);
                            Toast.makeText(getApplicationContext(),
                                    "Selected Button Rank :" + index,
                                    Toast.LENGTH_SHORT).show();
                            selectedCard = index;
                            isSelected = true;
                        }
                    }
                });
                count++;
            }
            gameStacks.addView(linearRow);
        }
    }
}

