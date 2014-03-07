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
    // new default constructor
    public GameMenuView(int numberOfPlayers) {
        this.game = new Game(numberOfPlayers);
    }
    // display the help menu and get the end users input selection
    public void getInput() { 
        String command;
        Scanner inFile = RectangleOfFortune.getInputFile();
        
        //ask for player names before going further
        for(int i=0;i<game.getNumberOfPlayers();i++) {
            game.getPlayerList()[i].setPlayerName(this.getPlayerName(i+1));
        }
        
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
                    if(game.getCurrentPlayer().getPlayerRoundBank() >= 250){
                        this.gameMenuControl.buyAVowel(game);
                    } else {
                        Messages.displayMessage("Not enough money to buy a vowel!");
                    }
                    break;
                case "P":
                    boolean result = this.gameMenuControl.solveThePuzzle(game);
                    if(result){
                        Messages.displayMessage(game.getCurrentPlayer().getPlayerName() 
                                + " wins! $" + game.getCurrentPlayer().getPlayerRoundBank()
                                + " in total winnings.");
                        game.getPuzzle().showWinningPuzzle();
                        Messages.displayMessage("Total turns played: "
                            + game.getTotalNumberOfTurns() + "."
                            + " Thanks for playing!");
                        command = "Q";
                    } else {
                        Messages.displayMessage("Sorry that is incorrect!");
                    }
                    break; 
                case "C":
                    this.gameMenuControl.showCurrentPlayerStanding(game.getPlayerList());
                    break;
                case "Q":
                    break;
                default: 
                    Messages.displayError("Invalid command. Please enter a valid command.");
            }
        } while (!command.equals("Q"));
    }
    
    // displays the help menu
    public final void displayGameMenu() {
        game.getPuzzle().displayPuzzle();
        
        String menuText = game.getCurrentPlayerName() + ", it's your turn. " + 
            "You currently have $" + game.getCurrentPlayer().getPlayerRoundBank() +
            ".\n\tEnter the letter associated with one of the following commands:";
        for (String[] menuItem : GameMenuView.menuItems) {
            menuText += "\n\t   " + menuItem[0] + "\t" + menuItem[1];
        }
        
        Messages.displayMessage(menuText);
    }
    
    private String getPlayerName(int playerNumber) {
        System.out.println();
        Messages.displayMessage("Player " + playerNumber + ", please enter your name");
        String name;
        Scanner inString = RectangleOfFortune.getInputFile();
        name=inString.nextLine().trim().toUpperCase();
        return name;
    } 
        
    }
