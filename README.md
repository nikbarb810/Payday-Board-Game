# Payday Board Game Java Implementation

## Introduction

This Java project is an implementation of the popular Payday board game. The game follows the Model-View-Controller (MVC) design pattern, dividing the codebase into three main packages: Model, View, and Controller.

![379463113_833322051586333_6522886164255680746_n](https://github.com/nikbarb810/Payday-Board-Game/assets/93132694/3e368a17-7428-46ee-ae9b-dafe41498719)



### Model Package
The Model package encompasses eight sub-packages:

1. **Cards**: Defines abstract classes for different types of cards, including deal and message cards.

2. **Deck**: Implements the two decks from which players draw cards (deal and message decks).

3. **Tile**: Defines an abstract class for various game tiles, such as buyer, casino, deal, and more.

4. **Board**: Represents the game board as an array of tiles.

5. **Player**: Contains variables describing player-specific data, such as balance, loan amount, score, and player cards.

6. **Pawn**: Implements the pawns used by each player.

7. **Dice**: Represents the dice used in the game.

8. **Jackpot**: Handles the game's jackpot functionality.

Below we can observe a UML diagram of the whole model package 
![image](https://github.com/nikbarb810/Payday-Board-Game/assets/93132694/9c70e648-61ae-4731-a649-fec824206fe6)



### Controller Package
The Controller package is responsible for facilitating communication between the Model and View components. It defines methods for initializing, controlling, and updating the game. Key methods include those for setting up decks, tiles, listeners for UI elements, determining game completion, finding the winner, and managing player actions during the game.

### View Package
The View package divides into three classes, each representing a portion of the graphical user interface (GUI):

1. **BoardPanel**: Extends JLayeredPane and creates the panel for displaying the game board and jackpot. It takes a Model instance as an argument and provides an `update()` method to refresh UI elements based on the game state.

2. **InfoBoxPanel**: Extends JTextArea and creates a panel for informing players of the game's status and required actions. It offers methods for updating player-specific information and messages.

3. **PlayerBoxPanel**: Extends JPanel and displays individual player information, including balance, loans, bills, and buttons for player actions. It takes a Player instance as an argument and provides an `update()` method to keep player data up to date.

4. **View**: Utilizes the above classes to construct the game's graphical user interface. It defines a JFrame as the main frame and offers methods for updating the UI components, such as players' information and game messages.

We also can picture how the View package is implemented in the following diagram.

![image](https://github.com/nikbarb810/Payday-Board-Game/assets/93132694/3473323d-2ea0-4993-b3c9-1e69f61d2dcb)



## Implementation Details

### Model
The Model component consists of various sub-packages, each responsible for different aspects of the game's logic, such as cards, tiles, players, and the game board. These packages contain classes and methods that collectively define the game's rules and state.

### Controller
The Controller component acts as an intermediary between the Model and View components. It defines methods for initializing the game, managing player actions, and handling game progression. The Controller makes decisions based on user input and updates the Model and View accordingly.

### View
The View component comprises three main classes, each responsible for rendering specific elements of the GUI. The View class orchestrates these components to create the complete game interface, ensuring that player information, the game board, and messages are displayed correctly.

A complete UML diagram of the whole project, highlights the MVC design pattern that was used, as the controller acts as a mediator between the Model and the View, whilst the latter do not have any immediate communication.

![image](https://github.com/nikbarb810/Payday-Board-Game/assets/93132694/d45de595-b9ac-4222-a12a-ffa69e187695)


## Conclusion

This Java implementation of the Payday board game adheres to the MVC design pattern, offering a structured and efficient way to model, control, and visualize the game. The separation of concerns in the Model, Controller, and View packages ensures a clear and maintainable codebase, making it easier to extend and enhance the game in the future.

For detailed information on how to run the game and its rules, refer to the project's PDF report.


## Author

- [Nikolaos Barmparousis](https://github.com/nikbarb810)

## License

This project is licensed under the [MIT License](LICENSE).
