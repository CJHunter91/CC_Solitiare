package com.codeclan.solitare;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by user on 11/08/2017.
 */

public class Deck {

    private ArrayList<String> ranks;
    private ArrayList<String> suits;
    private HashMap<String, Integer> cardValues;
    private HashMap<String, Integer> suitValues;
    private ArrayList<Card> deck;


    public Deck(){
        this.ranks = new ArrayList<>(Arrays.asList("A", "2", "3", "4", "5", "6", "7","8","9","10","J","Q","K"));
        this.suits = new ArrayList<>(Arrays.asList("S","H","D","C"));
        this.cardValues = new HashMap<>();
        this.suitValues = new HashMap<>();
        this.suitColour = new HashMap<>();
        this.deck = new ArrayList<>();


        this.suitValues.put("S", 0);this.suitValues.put("H", 1);this.suitValues.put("D", 2);
        this.suitValues.put("C", 3);


        genererateCardValues();
        genererateDeck();
        shuffleDeck();

    }

    private void genererateDeck() {
        for(String suit: this.suits){
            for(String rank: this.ranks){
                this.deck.add(new Card(rank,suit,false));
            }
        }
    }

    private void genererateCardValues(){

        int count = 0;

        for(String rank: this.ranks){
            this.cardValues.put(rank,count);
            count++;
        }
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }


    public int getCardValue(Card card) {
        return this.cardValues.get(card.getRank());
    }


    public int getSuitValue(Card card) {
        return this.suitValues.get(card.getSuit());
    }

    public void shuffleDeck(){
        Collections.shuffle(this.deck);
    }

    public Card draw(){
        Card card = deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
        return card;

    }



    @Override
    public String toString(){
        String s = "";
        for(Card card: this.deck){
            if(this.deck.indexOf(card) == this.deck.size()-1){
                s += card.getRank() + ":" + card.getSuit();
            }
            else{
                s += card.getRank() + ":" + card.getSuit() + ", ";
            }
        }
        return s;
    }
}
