package com.codeclan.solitare;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 11/08/2017.
 */

public class DeckTest {

    Deck deck;
    Card card;

    @Before
    public void before(){
        deck = new Deck();
        card = new Card("A", "D", true);
    }

    @Test
    public void canCreateCards(){
        ArrayList<Card> cardDeck = deck.getDeck();
        assertEquals(card.getClass(),cardDeck.get(0).getClass() );
    }
}
