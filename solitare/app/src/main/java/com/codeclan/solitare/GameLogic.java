package com.codeclan.solitare;

import java.util.ArrayList;

/**
 * Created by user on 11/08/2017.
 */

public class GameLogic {
    private ArrayList<ArrayList<Card>> aceStacks;
    private ArrayList<ArrayList<Card>> gameStacks;
    private ArrayList<Card> potentialMovesForCard;
    private Deck deck;

    public GameLogic(){
        this.deck = new Deck();
        this.gameStacks = new ArrayList<>();
        this.aceStacks = new ArrayList<>();
        this.potentialMovesForCard = new ArrayList<>();
        this.deck = new Deck();
    }

    public void newGame(){
        aceStacks.clear();
        gameStacks.clear();
        potentialMovesForCard.clear();
        deck = new Deck();

        buildGameStack();
        buildAceStack();
    }

    public ArrayList<ArrayList<Card>> getGameStacks(){
        return gameStacks;
    }

    private void buildGameStack(){
        //initialize the gameStacks array
        for(int i=0; i < 7; i++){
            gameStacks.add(new ArrayList<Card>());
        }

        int count = 0;

        for(int stack=0; stack < 7; stack++){
            for(int item=0; item <= count; item++){
                Card card = deck.draw();
                //reveal the last card
                if(item == count) {
                    card.reveal();
                }
                gameStacks.get(stack).add(card);
            }
            //used to add extra card next iteration
            count++;
        }
    }

}
