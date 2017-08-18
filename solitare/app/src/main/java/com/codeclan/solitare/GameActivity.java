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
import android.widget.ImageButton;
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


        ImageButton deckCard = (ImageButton) findViewById(R.id.draw);
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

    public void winToast(GameLogic game){
        if(game.gameWon()){
            Toast.makeText(getApplicationContext(),
                    "You Won in 'TIME HERE'",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void reDrawState(final GameLogic game, final LinearLayout gameStacks){
        int count = 0;
        gameStacks.removeAllViews();

        // display toast if game won
        winToast(game);
        //create pile cards and get pileCard
        final Button pileCard = pileCardSetup(gameStacks);
        aceCardSetup();
        //go through the gameStacks to display cards
        gameStackSetup(pileCard, gameStacks);

    }

    public Button pileCardSetup(final LinearLayout gameStacks){
        //create view for pile card
        final Button pileCard = (Button) findViewById(R.id.pile);
        pileCard.setTextSize(14);
        if(game.getPile().size() > 0) {
            final Card pileCardObject = game.getPileCard();
            pileCard.setTextColor(Color.BLACK);
            if(game.getColour(game.getPileCard()).equals("R")){
                pileCard.setTextColor(Color.RED);
            }

            pileCard.setText(pileCardObject.getRank() + pileCardObject.getSuit());
            pileCard.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.i("TAG", "index :" + "Pile");
                    game.moveToAce(pileCardObject);
                    reDrawState(game, gameStacks);
                    return true;
                }
            });
            pileCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.i("TAG", "index :" + Integer.toString(pileCard.getId()));
                    selectedCard = pileCard.getId();
                    isSelected = true;
                }
            });
        }
        else{
            pileCard.setText("");
        }

        return pileCard;
    }

    public void aceCardSetup(){

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
        int count = 0;
        for(final Button button : aceButtons){
            button.setText("");
            if(game.getAceStacks().get(count).size() > 0){
                String rank = game.getLastAceCard(count).getRank();
                String suit = game.getLastAceCard(count).getSuit();
                final Card card = game.getLastAceCard(count);
                button.setTextSize(14);
                button.setText(rank + suit);
                button.setTextColor(Color.BLACK);
                if(game.getColour(game.getLastAceCard(count)).equals("R")){
                    button.setTextColor(Color.RED);
                }
                gameState.put(button.getId(), card);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("TAG", "index :" + Integer.toString(button.getId()));
                        selectedCard = button.getId();
                        isSelected = true;
                    }
                });
            }
            count++;
        }

    }

    public void gameStackSetup(final Button pileCard, final LinearLayout gameStacks){

        int count = 0;
        for(final ArrayList<Card> stack : game.getGameStacks()) {
            LinearLayout linearColumn = new LinearLayout(this);
            linearColumn.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            linearColumn.setLayoutParams(params);
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
                cardButton.setTextSize(14);
                if(game.getColour(card).equals("R")){
                    cardButton.setTextColor(Color.RED);
                }
                //make the last items height larger
                if(stack.indexOf(card) == stack.size()-1) {
                    cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200));
                }
                else{
                    cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 120));
                }
                linearRow.addView(cardButton);
                linearColumn.addView(linearRow);
                final int index = count;

                cardButton.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Log.d("TAG", "index :" + Integer.toString(index));
                        game.moveToAce(card);
                        reDrawState(game, gameStacks);
                        return true;
                    }
                });
                CardMoveSpec cms = new CardMoveSpec(cardButton , stack, gameStacks, index, pileCard);
                // call to create button onclick listener
                gameCardButtonClickHandler(cms);
                count++;
            }
            // creates blank empty button
            if(stack.size() == 0){
                Button cardButton = new Button(this);
                cardButton.setText("");
                cardButton.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200));
                LinearLayout linearRow = new LinearLayout(this);
                linearRow.setOrientation(LinearLayout.HORIZONTAL);
                CardMoveSpec cms = new CardMoveSpec(cardButton , stack, gameStacks,pileCard);
                // call to create button onclick listener
                gameCardButtonClickHandler(cms);
                linearRow.setMinimumWidth(0);
                linearRow.addView(cardButton);
                linearColumn.addView(linearRow);
                count++;
            }

            gameStacks.addView(linearColumn);
        }
    }

    public void gameCardButtonClickHandler(final CardMoveSpec cms){
        final int index = cms.getIndex();
        final ArrayList<Card> stack = cms.getStack();
        cms.getCardButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelected && !game.gameWon()) {
                    Log.i("TAG", "index :" + Integer.toString(index));
                    if (selectedCard == cms.getPileCard().getId()) {
                        game.makeValidMove(game.getPileCard(),
                                game.getGameStacks().indexOf(stack));
                    } else {
                        game.makeValidMove(gameState.get(selectedCard),
                                game.getGameStacks().indexOf(stack));
                    }
                    reDrawState(game, cms.getGameStacks());
                    isSelected = false;
                } else if(gameState.containsKey(cms.getIndex())) {
                    Log.i("TAG", "index :" + Integer.toString(index));
                    selectedCard = index;
                    isSelected = true;
                }
            }

        });
    }


}

