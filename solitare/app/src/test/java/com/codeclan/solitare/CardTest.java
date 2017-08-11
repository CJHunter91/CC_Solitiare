package com.codeclan.solitare;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 11/08/2017.
 */

public class CardTest {

    Card card;

    @Before
    public void before(){
        card = new Card("A", "S", false);
    }

    @Test
    public void canGetRank(){
        assertEquals("A", card.getRank());
    }

    @Test
    public void canGetSuit(){
        assertEquals("S", card.getSuit());
    }
}
