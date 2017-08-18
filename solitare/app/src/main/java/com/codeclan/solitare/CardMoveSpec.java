package com.codeclan.solitare;

import android.widget.Button;
import android.widget.LinearLayout;

import com.codeclan.solitare.Card;

import java.util.ArrayList;

/**
 * Created by user on 18/08/2017.
 */

public class CardMoveSpec {
    private Button cardButton;
    private ArrayList<Card> stack;
    private LinearLayout gameStacks;
    private int index;
    private Button pileCard;

    public CardMoveSpec(Button cardButton, ArrayList<Card> stack, LinearLayout gameStacks, int index, Button pileCard){

        this.cardButton = cardButton;
        this.stack = stack;
        this.gameStacks = gameStacks;
        this.index = index;
        this.pileCard = pileCard;
    }

    public CardMoveSpec(Button cardButton, ArrayList<Card> stack, LinearLayout gameStacks, Button pileCard){

        this.cardButton = cardButton;
        this.stack = stack;
        this.gameStacks = gameStacks;
        this.pileCard = pileCard;
    }



    public Button getCardButton() {
        return cardButton;
    }

    public ArrayList<Card> getStack() {
        return stack;
    }

    public LinearLayout getGameStacks() {
        return gameStacks;
    }

    public int getIndex() {
        return index;
    }

    public Button getPileCard() {
        return pileCard;
    }
}

