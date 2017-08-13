shuffle the cards
place a card face up and lay 6 cards face down (7 total)
place a card face up in the second position and lay the rest face down 
contiue to there is a card face up in each column
draw pile not available until first click

a card can only be moved on to another card if -1 of the target card.
exceptions 
  kings can move to empty columns
  if an ace is placed in one of the four suit piles cards of +1 can be placed on them

helpful game logic
  maybe worth making playable cards a playable interface.
  cards that can be moved are the last item in the array i.e stack structure
  the draw pile card that is available could be calculated using a counter
    - i.e the card displayed has an index of the counter

Create the new game board(newGame)
  -clear all arrays
  -create a deck from Deck class()
  -iterate through the game Stacks
  -implement a count to add the correct number of cards to each stack
  -when adding the last card to each stack reveal it
  -reveal the first card from the remainder of the deck (variable deckCard)

Move a card on click
  -check there is an available move
  -MVP pick the first move from moves array
  -create a temp array with the cards to move
  -remove the cards from the array taken from 
  -add the cards to the end of the target array

double tap card(moveToAce)
  -check card is +1 greater than correct suit index of aceStack
  -move card to that stack and remove from game stack or pile

To Check for moves for specific card(get_Moves) potentially to get all possible moves
  -iterarte through each game stack
  -use the size of the array to get the last item
  -check the last item is +1 greater than moving cards  
  -also check that the card is of the opposite colour
  -add to get moves Hash


To get the next card in the deck pile
  -create pile array
  -remove last card from deck array
  -add to pile
  -remove from deck
  -when the last card from the deck is put on the pile and on next click
    deck = pile.reverse then clear pile

Move card from pile(moveFromPile)
  -check is valid move
  -remove from pile
  -add to appropriate stack

Check the game is won when size of all ace stacks are == 13

Display
Use seven instances of an adapter that take a stack array to display them.
onClick of each particular card will use move card


extensions
  have card backs in the row of cards
  save current game state and load on startup
  have a timer
  have number of moves
  have a ranking page to see fastest time and number of moves