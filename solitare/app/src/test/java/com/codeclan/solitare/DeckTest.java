package com.codeclan.solitare;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 11/08/2017.
 */

public class DeckTest {

    Deck deck;
    Card card;
    Card card2;

    @Before
    public void before(){
        deck = new Deck();
        card = new Card("A", "D", true);
        card2 = new Card("10", "C", false);
    }

    @Test
    public void canCreateCards(){
        ArrayList<Card> cardDeck = deck.getDeck();
        assertEquals(card.getClass(),cardDeck.get(0).getClass());
        assertEquals(card2.getClass(),cardDeck.get(cardDeck.size()-1).getClass());
    }

    @Test
    public void getCardValue(){
        assertEquals(0, deck.getCardValue(card));
        assertEquals(9, deck.getCardValue(card2));
    }

    @Test
    public void getSuitValue(){
        assertEquals(2, deck.getSuitValue(card));
        assertEquals(3, deck.getSuitValue(card2));

    }
    @Test
    public void getDeckIsCorrectSize(){
        assertEquals(52, deck.getDeck().size());
    }
    @Test
    public void deckToString(){
        deck.shuffleDeck();
        assertNotNull(deck.toString());
    }

    @Test
    public void drawCardTest(){
        assertEquals(card.getClass(), deck.draw());
    }
}
