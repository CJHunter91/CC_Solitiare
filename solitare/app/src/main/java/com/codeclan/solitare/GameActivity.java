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
        if(game.gameWon()){
            Toast.makeText(getApplicationContext(),
                    "You Won in 'TIME HERE'",
                    Toast.LENGTH_SHORT).show();
        }
        final GameLogic redrawGame = game;
        gameStacks.removeAllViews();
        int count = 0;
        //create view for pile card
        final Button pileCard = (Button) findViewById(R.id.pile);
        if(game.getPile().size() > 0) {
            final Card pileCardObject = redrawGame.getPileCard();
            pileCard.setTextColor(Color.BLACK);
            if(game.getColour(redrawGame.getPileCard()).equals("R")){
                pileCard.setTextColor(Color.RED);
            }

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
        for(final Button button : aceButtons){
            button.setText("");
            if(game.getAceStacks().get(count).size() > 0){
                String rank = game.getLastAceCard(count).getRank();
                String suit = game.getLastAceCard(count).getSuit();
                final Card card = game.getLastAceCard(count);
                button.setText(rank + suit);
                button.setTextColor(Color.BLACK);
                if(game.getColour(redrawGame.getLastAceCard(count)).equals("R")){
                    button.setTextColor(Color.RED);
                }
                gameState.put(button.getId(), card);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("TAG", "index :" + button.getId());
                        Toast.makeText(getApplicationContext(),
                                "Selected Button Rank :" + button.getId()+ card.isRevealed() ,
                                Toast.LENGTH_SHORT).show();
                        selectedCard = button.getId();
                        isSelected = true;
                    }
                });
            }
            count++;
        }

        //go through the gameStacks to display cards
        for(final ArrayList<Card> stack : game.getGameStacks()) {
            LinearLayout linearColumn = new LinearLayout(this);
            linearColumn.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            linearColumn.setLayoutParams(params);
            //need to create condition that if stack == 0 create one empty button
            for (final Card card : stack) {
                //update hash
                gameState.put(count, card);
                LinearLayout linearRow = new LinearLayout(this);
                linearRow.setOrientation(LinearLayout.HORIZONTAL);

                Button cardButton = new Button(this);
                if(card.isRevealed()){
                    cardButton.setText(card.getRank() + card.getSuit());
                }
                cardButton.setId(count);
                cardButton.setTextSize(10);
                if(game.getColour(card).equals("R")){
                    cardButton.setTextColor(Color.RED);
                }
                //make the last items height larger
                if(stack.indexOf(card) == stack.size()-1) {
                    cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200));
                }
                else{
                    cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100));
                }
                linearRow.addView(cardButton);
                linearColumn.addView(linearRow);
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
                                    "Clicked Button Rank :" + index + card.isRevealed(),
                                    Toast.LENGTH_SHORT).show();
                            if (selectedCard == pileCard.getId()){
                                redrawGame.makeValidMove(redrawGame.getPileCard(),
                                        redrawGame.getGameStacks().indexOf(stack));
                            }
                            else {
                                redrawGame.makeValidMove(gameState.get(selectedCard),
                                        redrawGame.getGameStacks().indexOf(stack));
                            }
                            reDrawState(redrawGame, gameStacks);
                            isSelected = false;
                        }
                        else{
                            Log.i("TAG", "index :" + index);
                            Toast.makeText(getApplicationContext(),
                                    "Selected Button Rank :" + index + card.isRevealed(),
                                    Toast.LENGTH_SHORT).show();
                            selectedCard = index;
                            isSelected = true;
                        }
                    }
                });
                count++;
            }
            if(stack.size() == 0){
                Button cardButton = new Button(this);
                cardButton.setText("");
                final int index = count;
                cardButton.setId(count);
                cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200));
                LinearLayout linearRow = new LinearLayout(this);
                linearRow.setOrientation(LinearLayout.HORIZONTAL);
                cardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(isSelected){
                            Log.i("TAG", "index :" + index);
                            Toast.makeText(getApplicationContext(),
                                    "Clicked Button Rank :" + index + "Empty",
                                    Toast.LENGTH_SHORT).show();
                            if (selectedCard == pileCard.getId()){
                                redrawGame.makeValidMove(redrawGame.getPileCard(),
                                        redrawGame.getGameStacks().indexOf(stack));
                            }
                            else{
                                redrawGame.makeValidMove(gameState.get(selectedCard),
                                        redrawGame.getGameStacks().indexOf(stack));
                            }
                            reDrawState(redrawGame, gameStacks);
                            isSelected = false;
                        }
                    }
                });
                linearRow.addView(cardButton);
                linearColumn.addView(linearRow);
                count++;
            }

            gameStacks.addView(linearColumn);
        }
    }
}

