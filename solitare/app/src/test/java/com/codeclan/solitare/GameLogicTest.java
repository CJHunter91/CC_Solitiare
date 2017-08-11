package com.codeclan.solitare;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 11/08/2017.
 */

public class GameLogicTest {
    GameLogic game;
    ArrayList<ArrayList<Card>> stacks;
    @Before
    public void before(){
        game = new GameLogic();
        stacks = game.getGameStacks();
    }

    @Test
    public void canSetupGameStacks(){
        game.newGame();
        assertEquals(7, stacks.size());
    }

    @Test
    public void testInnerStackSize(){
        int count = 1;
        for(ArrayList<Card> stack : stacks ){
            assertEquals(count, stack.size());
            count++;
        }
    }
    @Test
    public void testInnerStackCardReveal(){
        for(ArrayList<Card> stack : stacks){
            assertEquals(false, stack.get(stack.size()-1).isRevealed());
            assertEquals(true, stack.get(stack.size()-1).isRevealed());
        }
    }
    
    @Test
    public void testAceStacks(){
        assertEquals(4, game.getAceStacks().getClass());
    }

}
