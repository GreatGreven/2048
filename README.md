<h1>Hello this is my attempt to make the simple and fascinating v1.game of 2048 in Java. <br>
Enjoy!</h1>

Commits:
#1 The initial commit to get it up on git and github. I've already started working on the v1.game logic and v1.ui.

#2 Refactored packages and classes to have a proper, object oriented structure.

#3 Made the project "more" object-oriented and divided the Square and the Matrix classes and fixed broken v1.game logic and two major bugs, i.e. :
    1. The v1.game had a lot of semantic-errors which caused ArrayIndexOutOfBoundsException. This is because my first attempt to write the different move methods where sloppy and not thought all the way through.
    2. The v1.game also had a tendency to move the square first and then try to merged it but when tried to merge the square couldn't be found (because it was already moved), and it caused a NullPointerException. Again it was because caused of poorly thought semantics.

#4 Added the visual representation of the v1.game and implemented end-v1.game triggers in case the v1.game has been won or is over. Need to fix the spawnRandom-method because it spins forever when the board is full of tiles.

#5 Refactored name of class Square to Tile.

#6 Added the graphic and functionality in the windows when the v1.game is either won or is lost.
   Also fixed the never-ending spawning loop where if there where no tiles free then it could not randomly generate the a new tile.
   And Also refactored the class name of the previous Square-class to Tile, where it's a more accurate description of what it represents.

#7 Fixed the jump-merge bug, it was a simple semantics error.

#8 Fixed the multi-merge bug. Implemented a move counter for each v1.game and the v1.game is ready to display the score but a "score-system" needs to be implemented.
   Added a godmode for debugging purposes by pressing a secret key while in v1.game.

#9 Fixed the part of the multi-merge bug that only by a pattern of two types of tiles, i.e. 8, 4, 4, 8 as either row or col.
   This was actually a jump-merge bug that only appears when the same tiles of three are next to each other, and only the middle tile is already used.
   Implemented a score-system for the v1.game where you gain the amount of points of the new tile you've merged forth,
   i.e. merge 2 and 2 then you get a tile with the value of 4 and that's the points you get.

#10 Fixed that the frame always is displayed at the center of the screen. Added the META-INF folder to the .gitignore.

#11 Added package V2 for a new version of the game with animations and optimized logic.

Features left to implement:
    - Add animations so its easier to track the movement of the tiles.
        + componentFrames, FrameSets, multiple buffers/queues.
    - Change the old "move"-methods to a single move- and transpose-methods. 