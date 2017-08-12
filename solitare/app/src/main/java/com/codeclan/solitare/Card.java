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

    public String getSuit() {
        return suit;
    }

    public boolean isRevealed() {
        return revealed;
    }
    public void reveal(){
        this.revealed = true;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
}
