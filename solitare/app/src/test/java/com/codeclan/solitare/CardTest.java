package com.codeclan.solitare;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 11/08/2017.
 */

public class CardTest {

    Card card;
    Card card2;

    @Before
    public void before(){
        card = new Card("A", "S", false);
        card2 = new Card("5", "D", true);
    }

    @Test
    public void canGetRank(){
        assertEquals("A", card.getRank());
    }

    @Test
    public void canGetSuit(){
        assertEquals("S", card.getSuit());
    }

    @Test
    public void isRevealed(){
        assertEquals(false, card.isRevealed());
        assertEquals(true, card2.isRevealed());
    }

    @Test
    public void  canSetRank(){
        card.setRank("4");
        assertEquals("4", card.getRank());
    }

    @Test
    public void canSetSuit(){
        card.setSuit("C");
        assertEquals("C", card.getSuit());
    }
}
