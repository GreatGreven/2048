<h1>Hello this is my attempt to make the simple and fascinating game of 2048 in Java. <br>
Enjoy!</h1>

Commits:
#1 The initial commit to get it up on git and github. I've already started working on the game logic and ui.

#2 Refactored packages and classes to have a proper, object oriented structure.

#3 Fixed broken game logic and two major bugs, i.e. :
    1. The game had a lot of semantic-errors which caused ArrayIndexOutOfBoundsException. This is because my first attempt to write the different move methods where sloppy and not thought all the way through.
    2. The game also had a tendency to move the square first and then try to merged it but when tried to merge the square couldn't be found (because it was already moved), and it caused a NullPointerException. Again it was because caused of poorly thought semantics.