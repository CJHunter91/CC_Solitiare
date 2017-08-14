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
//        game.newGame();
//        deck = game.getRemainingDeck();
//        stacks = game.getGameStacks();
//        game.getCard(1,1).setRank("10");
//        game.getCard(1,1).setSuit("D");
//        game.getCard(2,2).setRank("9");
//        game.getCard(2,2).setSuit("C");


    }

    @Test
    public void canSetupGameStacks(){
        game.newGame();
        stacks = game.getGameStacks();
        assertEquals(7, stacks.size());

    }

    @Test
    public void testInnerStackSize(){
        game.newGame();
        stacks = game.getGameStacks();

        int count = 1;
        for(ArrayList<Card> stack : stacks ){
            assertEquals(count, stack.size());
            count++;
        }
    }
    @Test
    public void testInnerStackCardReveal(){
        game.newGame();
        deck = game.getRemainingDeck();
        stacks = game.getGameStacks();
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
        deck = game.getRemainingDeck();
        stacks = game.getGameStacks();
        stacks.get(2).get(1).reveal();
        assertEquals(true, stacks.get(2).get(1).isRevealed());
    }

    @Test
    public void canGetCard(){
        game.newGame();
        stacks = game.getGameStacks();
        assertEquals(stacks.get(2).get(1), game.getCard(2,1));
    }

    @Test
    public void testCanMoveCard(){
        game.newGame();
        stacks = game.getGameStacks();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("9");
        game.getCard(2,2).setSuit("C");
        Card card = game.getCard(2,1);
//        System.out.println(stacks.get(2)+"move");
//        System.out.println(stacks.get(1)+"Targe
        game.move(card,1);
//        System.out.println(stacks.get(2)+"move");
//        System.out.println(stacks.get(1)+"Target");
        assertEquals(game.getCard(1,2),card);
        assertEquals(1,stacks.get(2).size());
    }

    @Test
    public void testCanMoveCardLarge(){
        game.newGame();
        stacks = game.getGameStacks();
        Card card = game.getCard(6,0);
        game.move(card ,0);
        assertEquals(game.getCard(0,1), card);
        assertEquals(8,stacks.get(0).size());
    }

    @Test
    public void testIsValidMove(){
        game.newGame();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("9");
        game.getCard(2,2).setSuit("C");
        assertEquals(true, game.isValidMove(game.getCard(2,2),game.getCard(1,1)));
    }
    @Test
    public void testCanGetPotentialMoves(){
        game.newGame();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("9");
        game.getCard(2,2).setSuit("C");
        System.out.println(game.getMoves(2,2));
        assertEquals(true,game.getMoves(2,2).containsKey(1));
        assertEquals(true,game.getMoves(2,2).containsValue(1));

    }

    @Test
    public void remainingDeckIsCorrectSize(){
        game.newGame();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("9");
        game.getCard(2,2).setSuit("C");
        assertEquals(24, game.getRemainingDeck().size());
    }

    @Test
    public void testCanDrawFromDeckToPile(){
        game.newGame();
        deck = game.getRemainingDeck();
        stacks = game.getGameStacks();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("9");
        game.getCard(2,2).setSuit("C");
        Card card = deck.get(deck.size()-1);
        game.deckDraw();
        assertEquals(card, game.getPileCard());

    }

    @Test
    public void testCanDrawMultipleCards(){
        game.newGame();
        deck = game.getRemainingDeck();
        stacks = game.getGameStacks();
        Card card = deck.get(deck.size()-3);
        game.deckDraw();
        game.deckDraw();
        game.deckDraw();
        assertEquals(card, game.getPileCard());
    }

    @Test
    public void testCanDrawAllCards(){
        game.newGame();
        deck = game.getRemainingDeck();
        for(int i = 0; i < 24; i++){
            game.deckDraw();
        }
        assertEquals(0,deck.size());
    }

    @Test
    public void testCanDrawAllCardsThenResetPile(){
        game.newGame();
        deck = game.getRemainingDeck();
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
        ArrayList<Integer> find = new ArrayList<>();
        find.add(1);
        find.add(1);
        assertEquals(find, game.findCard(game.getCard(1,1)));
    }

    @Test
    public void testCanFindCardInPile(){
        game.newGame();
        game.deckDraw();
        ArrayList<Integer> find = new ArrayList<>();
        find.add(-1);
        find.add(0);
        assertEquals(find, game.findCard(game.getCard(-1,0)));
    }

    @Test
    public void testCanGetMovesForPileCard(){
        game.newGame();
        game.deckDraw();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("9");
        game.getCard(2,2).setSuit("C");
        game.getPileCard().setRank("9");
        game.getPileCard().setSuit("C");
        assertEquals(true,game.getMoves(-1,-1).containsKey(1));
        assertEquals(true,game.getMoves(-1,-1).containsValue(1));

    }

    @Test
    public void testCanMovePileCard(){
        game.newGame();
        game.deckDraw();
        game.getPileCard().setRank("9");
        game.getPileCard().setSuit("C");
        Card card = game.getPileCard();
        game.movePileCard(1);
        assertEquals(card, game.getCard(1,2));
    }

    @Test
    public void testCanMoveToAceStack(){
        game.newGame();
        game.getCard(0,0).setRank("A");
        Card card =  game.getCard(0,0);
        game.moveToAce(game.getCard(0,0));
        assertEquals(card ,game.getAceStacks().get(0).get(0));

    }

    @Test
    public void testCanRemoveCardFromGameStack(){
        game.newGame();
        game.removeCard(0,0);
        assertEquals(0, game.getGameStacks().get(0).size());
    }

    @Test
    public void testCanRemoveCardFromPile(){
        game.newGame();
        this.game.deckDraw();
        this.game.deckDraw();
        game.removeCard(-1,0);

        assertEquals(1, game.getPile().size());
    }

    @Test
    public void testCanRemoveCardFromPile2(){
        game.newGame();
        this.game.deckDraw();
        game.removeCard(-1,0);

        assertEquals(0, game.getPile().size());
    }

    @Test
    public void testMoveToAceStackRemovesGameStackCard(){
        game.newGame();
        game.getCard(0,0).setRank("A");
        Card card = game.getCard(0,0);
        game.moveToAce(game.getCard(0,0));
        assertEquals(card ,game.getAceStacks().get(0).get(0));
        assertEquals(0, game.getGameStacks().get(0).size());
    }

    @Test
    public void testMoveToAceStackFromPile(){
        game.newGame();
        game.deckDraw();
        game.getCard(-1,0).setRank("A");
        Card card = game.getCard(-1,0);
        game.moveToAce(game.getCard(-1,0));
        assertEquals(card ,game.getAceStacks().get(0).get(0));
        assertEquals(0, game.getPile().size());
    }


    @Test
    public void makeValidMove(){
        game.newGame();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("9");
        game.getCard(2,2).setSuit("C");
        Card card1 = game.getCard(1,1);
        Card card2 = game.getCard(2,2);
        game.makeValidMove(card2, card1);
        assertEquals(card2, game.getCard(1,2));
    }

    @Test
    public void makeValidMoveLarge(){
        game.newGame();
        game.getCard(1,1).setRank("10");
        game.getCard(1,1).setSuit("D");
        game.getCard(2,2).setRank("7");
        game.getCard(2,2).setSuit("C");
        game.getCard(2,1).setRank("8");
        game.getCard(2,1).setSuit("D");
        game.getCard(2,0).setRank("9");
        game.getCard(2,0).setSuit("C");
        game.getCard(2,0).reveal();
        game.getCard(2,2).reveal();
        game.getCard(2,1).reveal();
        Card card1 = game.getCard(1,1);
        Card card2 = game.getCard(2,2);
        Card card3 = game.getCard(2,0);
        game.makeValidMove(card3, card1);
        assertEquals(card3, game.getCard(1,2));
        assertEquals(card2, game.getCard(1,4));
    }



}
