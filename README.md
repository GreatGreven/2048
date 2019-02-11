<h1>Hello this is my attempt to make the simple and fascinating game of 2048 in Java. <br>
Enjoy!</h1>

Commits:
#1 The initial commit to get it up on git and github. I've already started working on the game logic and ui.

#2 Refactored packages and classes to have a proper, object oriented structure.

#3 Made the project "more" object-oriented and divided the Square and the Matrix classes and fixed broken game logic and two major bugs, i.e. :
    1. The game had a lot of semantic-errors which caused ArrayIndexOutOfBoundsException. This is because my first attempt to write the different move methods where sloppy and not thought all the way through.
    2. The game also had a tendency to move the square first and then try to merged it but when tried to merge the square couldn't be found (because it was already moved), and it caused a NullPointerException. Again it was because caused of poorly thought semantics.

#4 Added the visual representation of the game and implemented end-game triggers in case the game has been won or is over. Need to fix the spawnRandom-method because it spins forever when the board is full of tiles.

#5 Refactored name of class Square to Tile.

#6 Added the graphic and functionality in the windows when the game is either won or is lost.
       Also fixed the never-ending spawning loop where if there where no tiles free then it could not randomly generate the a new tile.
        And Also refactored the class name of the previous Square-class to Tile, where it's a more accurate description of what it represents.

Features left to implement:
    - A score/move counter that represents the score/moves of the game.
    - Invent a score-system for the game.

Bugs left to fix:
    - A tile sometimes jumps over another tile to merge a third tile.

