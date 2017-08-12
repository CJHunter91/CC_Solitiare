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

Click on ace spot(moveToAce)
  -check the size of the array for the specific suit
  -check if there is a value +1 greater available
  -use similar logic(as move a card) to move target card to ace stack
  -if size is >= 13 don't do anything.

To Check for moves for specific card(get_Moves) potentially to get all possible moves
  -iterarte through each game stack
  -use the size of the array to get the last item
  -check the last item is +1 greater than moving cards  
  -also check that the card is of the opposite colour
  -add to get moves array


To get the next card in the deck pile
  -when clicked add one to the counter
  -update by using the index of the deck
  -loop back to 0 when >than size of the array

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