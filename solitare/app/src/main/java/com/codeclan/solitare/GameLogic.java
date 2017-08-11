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



    }
}
