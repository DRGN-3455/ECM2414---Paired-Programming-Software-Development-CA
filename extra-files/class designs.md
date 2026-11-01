# Card 
## Methods 
- Card ( int value ) - done
- getValue() - done
- toString() -done
## Tests: - passes all
- testGetValue() – initialises a new card object and performs a getValue() on it and matches that to the expected result through an assert.
- testToString() – initialises new card, calls the toString() method and compares it to the expected result through an assert.
- Independent test – tested with a string input into the attribute and received a compilation level error – as expected
# CardDeck - Threadsafe

## Methods 
- addCard(card) - adds card to bottom of deck - done
- drawCard() - takes and returns a card from the top of the deck - done
- getDeckFIFO() - returns an array of the cards in the deck – done
- getCardValues – returns an array containing the value of each Card present in the deck
- getSize() – unsure.
- getDeckId() – returns id of Deck
- resetDeck() – resets the deck counter.
- writeOutput () - write deck contents to a .txt file – done.
## Tests: - passes all
- testAddCard() – done 
- testDrawCard() – done 
- testGetDeckFIFO() – done
- testGetCardValues() – done
- testGetSize() – done, only 1 way, please check me
- testGetDeckId() – done
- testResetDeck() – done
- testWriteOutput() - done

# Player 
## Methods: 
- Player ( int id, CardDeck leftDeck, CardDeck rightDeck) 
- run() - Checks if there is a winner immediately after dealing and runs the player’s strategy until they have won.
- addToHand(Card card) 
- getHand() -  returns list of hand contents
- hasWinningHand() - returns boolean. If has four cards of same value
- takeTurn() 

# CardGame
## Methods
- main()
- askNumberOfPlayers() – validate number of players inside I’m assuming (> 1)
- askForPackFile()
- validatePack(File, int n) - Checks there are 8n rows and no nevative ints for card values
- readPack(File) - returns List<Card>
- createDecksAndPlayers(int n) - Instatiates n players and their respective decks
- dealHands(List<Card>, List<Player>) - this is the round robin, 4 cards each
- FillDecks(List<Card>, List<CardDeck>) - round robin the remainder to the decks
- checkImmediateWin(List<Player>) - This just check hasWinningHand() on each player
- startThreads(List<Player>)
- endGame()?
- writeDeckOutputs(List<CardDeck>) - runs writeOutput() for each deck



