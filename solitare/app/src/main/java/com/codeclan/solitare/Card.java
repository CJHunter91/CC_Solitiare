package com.codeclan.solitare;

/**
 * Created by user on 11/08/2017.
 */

public class Card {
    private String rank;
    private String suit;
    private boolean revealed;


    public Card(String rank, String suit, boolean revealed){
        this.rank = rank;
        this.suit = suit;
        this.revealed = revealed;
    }

    public String getRank() {
        return rank;
    }
}
