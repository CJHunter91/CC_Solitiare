package com.codeclan.solitare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

    public void move(int stack, int stackItem, int targetStack){
        ArrayList<Card> temp = new ArrayList<>();
        //iterate through the stack to add each card to temp in prep for move
        //removing each card from the moving stack
        for(int i = this.gameStacks.get(stack).size()-1; i >= stackItem; i--) {
            temp.add(getCard(stack, i));
            this.gameStacks.get(stack).remove(i);
        }
        //adds each card from temp to the target stack
        for(int j = temp.size()-1; j >= 0; j--){
            this.gameStacks.get(targetStack).add(temp.get(j));

        }
    }

    public ArrayList<ArrayList<Card>> getGameStacks(){
        return gameStacks;
    }

    public ArrayList<ArrayList<Card>> getAceStacks(){
        return aceStacks;
    }

    public Card getCard(int stack, int stackItem){
        return gameStacks.get(stack).get(stackItem);
    }

    public HashMap<Integer, Integer> getMoves(int stack, int stackItem){

    }



    private void  buildAceStack(){
        for(int i=0; i <4; i++){
            aceStacks.add(new ArrayList<Card>());
        }
    }

    private void buildGameStack(){

        int count = 0;

        for(int stack=0; stack < 7; stack++){
            gameStacks.add(new ArrayList<Card>());
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
