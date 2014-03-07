package rectangleoffortune;

import java.util.Scanner;

/**
 *
 * @author Joseph/Dustin
 */
public class GameMenuView  {
    Game game;
    private final static String[][] menuItems = {
        {"S", "Spin"},
        {"B", "Buy a Vowel"},
        {"P", "Solve the puzzle"},
        {"C", "Current Player Bank Standing"},
        {"Q", "Quit Game"}
    };
    
    // Create instance of the HelpMenuControl (action) class
    private final GameMenuControl gameMenuControl = new GameMenuControl();
    
    // default constructor
    public GameMenuView(Game game) {
        this.game=game;
    } 
    
    // display the help menu and get the end users input selection
    public void getInput() { 
        String command;
        Scanner inFile = new Scanner(System.in);
        
        do {
            
            this.displayGameMenu(); // display the menu
            
            // get commaned entered
            command = inFile.nextLine();
            command = command.trim().toUpperCase();
            
            switch (command) {
                case "S":
                    this.gameMenuControl.spin(game);
                    break;
                case "B":
                    if(game.getCurrentPlayer().playerBank_Round >= 250){
                        this.gameMenuControl.buyAVowel(game);
                    } else {
                        new Messages().displayMessage("Not enough money to buy a vowel!");
                    }
                    break;
                case "P":
                    boolean result = this.gameMenuControl.solveThePuzzle(game);
                    if(result){
                        new Messages().displayMessage(game.getCurrentPlayer().playerName + " wins!");
                        game.puzzle.showWinningPuzzle();
                        command = "Q";
                    } else {
                        new Messages().displayMessage("Sorry that is incorrect!");
                    }
                    break; 
                case "C":
                    this.game.showCurrentPlayerStanding();
                    break;
                case "Q":
                    break;
                default: 
                    new Messages().displayError("Invalid command. Please enter a valid command.");
            }
        } while (!command.equals("Q"));
    }

    public String getPlayerName(int playerNumber) {
        this.gameMenuControl.promptForPlayerName(playerNumber);
        String name;
        Scanner inString = new Scanner(System.in);
        name=inString.nextLine().trim().toUpperCase();
        return name;
    }    
    
    // displays the help menu
    public final void displayGameMenu() {
        game.puzzle.displayPuzzle();
        
        String menuText = "";
        menuText += game.getCurrentPlayerName() + ", it's your turn. " + 
            "You currently have $" + game.getCurrentPlayer().playerBank_Round +
            ".\n\tEnter the letter associated with one of the following commands:";
        for (String[] menuItem : GameMenuView.menuItems) {
            menuText += "\n\t   " + menuItem[0] + "\t" + menuItem[1];
        }
        
        new Messages().displayMessage(menuText);
    }
}
