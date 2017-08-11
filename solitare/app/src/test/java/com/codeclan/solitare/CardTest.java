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
        card = new Card("A", "S");
    }
    
    @Test
    public void canGetSuit(){
        assertEquals("A", card.getRank());
    }
}
