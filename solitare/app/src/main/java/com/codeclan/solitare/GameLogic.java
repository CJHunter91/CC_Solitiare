package com.codeclan.solitare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by user on 11/08/2017.
 */

public class GameLogic {
    private ArrayList<ArrayList<Card>> aceStacks;
    private ArrayList<ArrayList<Card>> gameStacks;
    private Deck deck;
    private ArrayList<Card> pile;

    public GameLogic(){
        this.deck = new Deck();
        this.gameStacks = new ArrayList<>();
        this.aceStacks = new ArrayList<>();
        this.pile = new ArrayList<>();
    }

    private void  buildAceStack(){
        for(int i=0; i <4; i++){
            aceStacks.add(new ArrayList<Card>());
        }
    }

    private void buildGameStack(){

        int count = 0;

        for(int stack=0; stack < 7; stack++){
            gameStacks.add(new ArrayList<Card>());
            for(int item=0; item <= count; item++){
                Card card = deck.draw();
                //reveal the last card
                if(item == count) {
                    card.reveal();
                }
                gameStacks.get(stack).add(card);
            }
            //used to add extra card next iteration
            count++;
        }
    }

    public ArrayList<ArrayList<Card>> getGameStacks(){
        return gameStacks;
    }

    public ArrayList<ArrayList<Card>> getAceStacks(){
        return aceStacks;
    }

    public String getColour(Card card){
        return deck.getSuitColour(card);
    }
    public Card getCard(int stack, int stackItem){
        if(stack == -1){
            return this.pile.get(stackItem);
        }

        return gameStacks.get(stack).get(stackItem);

    }


    public Card getLastAceCard(int stack){
        return this.aceStacks.get(stack).get(this.aceStacks.get(stack).size() -1);
    }
    public ArrayList<Card> getRemainingDeck() {
        return this.deck.getDeck();
    }

    public Card getPileCard(){
        if(getPile().size() >= 1) {
            Card card = this.pile.get(pile.size() - 1);
            return card;
        }
        return null;
    }

    public HashMap<Integer, Integer> getMoves(Card card){
        HashMap<Integer,Integer> moves = new HashMap<>();
        Card moveCard = null;
        ArrayList<Integer> find = findCard(card);
        int stack = find.get(0);
        int stackItem = find.get(1);
        if(stack == -1){
            moveCard = this.pile.get(stackItem);
        }
        else {
            moveCard = getCard(stack, stackItem);
        }
        for(int i =0; i < 7; i++){
            int stackSize = gameStacks.get(i).size() - 1;
            if(i != stack && isValidMove(moveCard, getCard(i, stackSize) )){
                moves.put(i,stackSize);
            }
        }
        return moves;
    }

    public void removeCard(int stack, int stackItem) {
        if(stack == -1){
            this.pile.remove(stackItem);
        }
        else {
            this.gameStacks.get(stack).remove(stackItem);
        }
    }

    public ArrayList<Integer> findCard(Card card) {
        ArrayList<Integer> find = new ArrayList<>();
        for(ArrayList<Card> stack: this.gameStacks) {
            for (Card eachCard : stack) {
                if (card == eachCard) {
                    find.add(this.gameStacks.indexOf(stack));
                    find.add(stack.indexOf(eachCard));
                }
            }
        }
        if(find.size() == 0){
            for(Card eachCard: this.pile){
                if(card == eachCard){
                    find.add(-1);
                    find.add(this.pile.indexOf(eachCard));
                }
            }
        }

        return find;

    }

    public ArrayList<Card> getPile() {
        return pile;
    }

    public void newGame(){
        pile.clear();
        aceStacks.clear();
        gameStacks.clear();
        deck = new Deck();

        buildGameStack();
        buildAceStack();
    }

    public void deckDraw(){
        if(getRemainingDeck().size() <= 0){
            this.deck.setDeck(new ArrayList<>(pile));
            Collections.reverse(getRemainingDeck());
            pile.clear();
        }
        else {
            Card drawCard = getRemainingDeck().get(getRemainingDeck().size() - 1);
            getRemainingDeck().remove(getRemainingDeck().size() - 1);
            drawCard.reveal();
            this.pile.add(drawCard);
        }
    }

    public void move(Card card, int targetStack){
        ArrayList<Card> temp = new ArrayList<>();
        //iterate through the stack to add each card to temp in prep for move
        //removing each card from the moving stack
        ArrayList<Integer> find = findCard(card);
        int stack = find.get(0);
        int stackItem = find.get(1);

        for(int i = this.gameStacks.get(stack).size()-1; i >= stackItem; i--) {
            temp.add(getCard(stack, i));
            this.gameStacks.get(stack).remove(i);
        }
        //adds each card from temp to the target stack
        for(int j = temp.size()-1; j >= 0; j--){
            this.gameStacks.get(targetStack).add(temp.get(j));

        }
        if(getGameStacks().get(stack).size() > 0){
            getCard(stack, stackItem - 1).reveal();
        }
    }

    public void movePileCard(int targetStack){
        Card moveCard = getPileCard();
        this.gameStacks.get(targetStack).add(moveCard);
        this.pile.remove(this.pile.size()-1);

    }

    public void moveToAce(Card card){
        //needs to remove the card if added
        if(card.isRevealed()) {
            for (ArrayList<Card> stack : getAceStacks()) {
                int cardValue = this.deck.getCardValue(card);
                if (stack.size() == 0 && card.getRank().equals("A")) {
                    stack.add(card);
                    ArrayList<Integer> find = findCard(card);
                    System.out.println(find);
                    removeCard(find.get(0), find.get(1));
                    break;
                } else if (stack.size() > 0 && stack.size() < 13) {
                    Card lastInAceStack = getLastAceCard(aceStacks.indexOf(stack));
                    if (this.deck.getCardValue(lastInAceStack) == cardValue - 1 &&
                            lastInAceStack.getSuit().equals(card.getSuit())) {
                        stack.add(card);
                        ArrayList<Integer> find = findCard(card);
                        removeCard(find.get(0), find.get(1));
                        break;
                    }
                }

            }
        }
    }

    public boolean isValidMove(Card moveCard, Card targetCard) {
        int moveValue = deck.getCardValue(moveCard);
        int targetValue = deck.getCardValue(targetCard);
        String moveColour = deck.getSuitColour(moveCard);
        String targetColour = deck.getSuitColour(targetCard);

        if(moveCard.isRevealed() && targetCard.isRevealed()){
            if(targetValue == (moveValue+1)){
                if(moveColour != targetColour){
                    return true;
                }

            }

        }

        return false;
    }

    public void makeValidMove(Card moveCard, int targetStack){


        if(this.gameStacks.get(targetStack).size() > 0){
            Card targetCard = getCard(targetStack, this.gameStacks.get(targetStack).size()-1);
            if(isValidMove(moveCard,targetCard)){
                move(moveCard, targetStack);
            }
        }
        else if(moveCard.getRank().equals("K")){
            move(moveCard, targetStack);
        }
    }






}
