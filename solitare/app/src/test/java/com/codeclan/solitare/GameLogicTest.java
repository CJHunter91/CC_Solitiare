package com.codeclan.solitare;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by user on 11/08/2017.
 */

public class GameLogicTest {
    GameLogic game;
    ArrayList<ArrayList<Card>> stacks;
    ArrayList<Card> deck;
    @Before
    public void before(){
        game = new GameLogic();
        game.newGame();
        deck = game.getRemainingDeck();
        stacks = game.getGameStacks();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("9");
        game.getCard(2,2).setSuit("C");

    }

    @Test
    public void canSetupGameStacks(){
        game.newGame();
        assertEquals(7, stacks.size());

    }

    @Test
    public void testInnerStackSize(){
        game.newGame();
        int count = 1;
        for(ArrayList<Card> stack : stacks ){
            assertEquals(count, stack.size());
            count++;
        }
    }
    @Test
    public void testInnerStackCardReveal(){
        game.newGame();
        int count =0;
        for(ArrayList<Card> stack : stacks){
            if(count>0) {
                assertEquals(false, stack.get(stack.size() - 2).isRevealed());
                count++;
            }
            assertEquals(true, stack.get(stack.size()-1).isRevealed());
        }
    }

    @Test
    public void testAceStacks(){
        game.newGame();
        assertEquals(4, game.getAceStacks().size());
    }

    @Test
    public void testCanChangeCardtoRevealed(){
        game.newGame();
        stacks.get(2).get(1).reveal();
        assertEquals(true, stacks.get(2).get(1).isRevealed());
    }

    @Test
    public void canGetCard(){
        game.newGame();
        assertEquals(stacks.get(2).get(1), game.getCard(2,1));
    }

    @Test
    public void testCanMoveCard(){
        game.newGame();
        Card card = game.getCard(2,1);
//        System.out.println(stacks.get(2)+"move");
//        System.out.println(stacks.get(1)+"Target");
        game.move(2,1,1);
//        System.out.println(stacks.get(2)+"move");
//        System.out.println(stacks.get(1)+"Target");
        assertEquals(game.getCard(1,2),card);
        assertEquals(1,stacks.get(2).size());
    }

    @Test
    public void testCanMoveCardLarge(){
        game.newGame();
        Card card = game.getCard(6,0);
        game.move(6,0,0);
        assertEquals(game.getCard(0,1), card);
        assertEquals(8,stacks.get(0).size());
    }

    @Test
    public void testIsValidMove(){
        assertEquals(true, game.isValidMove(game.getCard(2,2),game.getCard(1,1)));
    }
    @Test
    public void testCanGetPotentialMoves(){
        System.out.println(game.getMoves(2,2));
        assertEquals(true,game.getMoves(2,2).containsKey(1));
        assertEquals(true,game.getMoves(2,2).containsValue(1));

    }

    @Test
    public void remainingDeckIsCorrectSize(){
        assertEquals(24, deck.size());
    }

    @Test
    public void testCanDrawFromDeckToPile(){
        Card card = deck.get(deck.size()-1);
        game.deckDraw();
        assertEquals(card, game.getPileCard());

    }

    @Test
    public void testCanDrawMultipleCards(){
        Card card = deck.get(deck.size()-3);
        game.deckDraw();
        game.deckDraw();
        game.deckDraw();
        assertEquals(card, game.getPileCard());
    }

    @Test
    public void testCanDrawAllCards(){
        for(int i = 0; i < 24; i++){
            game.deckDraw();
        }
        assertEquals(0,deck.size());
    }

    @Test
    public void testCanDrawAllCardsThenResetPile(){
        Card card = deck.get(deck.size()-1);
        for(int i = 0; i <= 24; i++){
            game.deckDraw();
        }
        Card resetCard = game.getRemainingDeck().get(game.getRemainingDeck().size()-1);
        assertEquals(card, resetCard);
        assertEquals(0, game.getPile().size());
        assertEquals(24,game.getRemainingDeck().size());
    }

    @Test
    public void testCanFindCardInGame(){
        game.newGame();
        HashMap<Integer,Integer> find = new HashMap<>();
        find.put(1,1);
        assertEquals(find, game.findCard(game.getCard(1,1)));
    }

//    @Test
//    public void testCanFindCardInDeck(){
//        game.newGame();
//        HashMap<Integer,Integer> find = new HashMap<>();
//        find.put(1,1);
//        assertEquals(find, game.findCard(game.getCard(1,1)));
//    }

    @Test
    public void testCanGetMovesForPileCard(){
        game.deckDraw();
        game.getPileCard().setRank("9");
        game.getPileCard().setSuit("C");
        assertEquals(true,game.getMoves(-1,-1).containsKey(1));
        assertEquals(true,game.getMoves(-1,-1).containsValue(1));

    }




}
