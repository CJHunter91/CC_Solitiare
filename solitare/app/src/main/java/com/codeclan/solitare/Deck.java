package com.codeclan.solitare;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by user on 11/08/2017.
 */

public class Deck {

    ArrayList<String> ranks;
    ArrayList<String> suits;
    HashMap<String, Integer> cardValues;
    HashMap<String, Integer> suitValues;
    ArrayList<Card> deck;


    public Deck(){
        this.ranks = new ArrayList<>(Arrays.asList("A", "2", "3", "4", "5", "6", "7","8","9","10","J","Q","K"));
        this.suits = new ArrayList<>(Arrays.asList("S","H","D","C"));
        this.cardValues = new HashMap<>();
        this.suitValues = new HashMap<>();
        this.deck = new ArrayList<>();


        this.suitValues.put("S", 0);this.suitValues.put("H", 1);this.suitValues.put("D", 2);
        this.suitValues.put("C", 0);


        genererateCardValues();
        genererateDeck();

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





}
