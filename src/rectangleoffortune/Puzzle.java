/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rectangleoffortune;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Joseph/Dustin
 */
public class Puzzle {
    private String puzzleText;
    private int puzzleNumWords;
    int remainingLetters = 0;
    Letter currentPuzzle[];
    int remainingVowels; //=3;
//    int puzzleLength; // = puzzleText.length();
    private List<String> puzzleList;
       
    public Puzzle() {
        puzzleList=new ArrayList();
        populatePuzzleList();
        selectNewPuzzle();
    }
    
    public void selectNewPuzzle() {
        //random puzzle picker
        int highNum = puzzleList.size(); //highest number needed
        int i; // to keep track of the index
        
        i = getRandomNumber(highNum);  //generate the random number
//        System.out.println("Index is " + i);
        puzzleText = this.puzzleList.get(i); //pick the random puzzle from the array
        this.puzzleList.remove(i); //remove the puzzle so we don't use it again during this game
        
        //create the array of letters
        currentPuzzle= new Letter[puzzleText.length()];

        //populate the array with each letter object
        for(i=0;i<=puzzleText.length()-1;i++) {
            currentPuzzle[i]=new Letter(puzzleText.charAt(i));
        }

        //for testing
//        System.out.println("Puzzle Text is :" + puzzleText);
//        System.out.println("Puzzle characters are:");
//        for(Letter x: currentPuzzle) {
//            System.out.println("Letter: " + x.value + "\n\t"
//                    + "IsDisplayed? " + x.isVisible + "\n\t"
//                    + "IsSpace?" + x.isSpace + ".\n");
//        }
    }
    private int getRandomNumber(int highNum) {
        //returns an int between 1 and the number provided
        Random rand = new Random();
        int i;
        i = rand.nextInt(highNum);
        return i;
    }
    
//    private String selectNewPuzzle() {
////        String newPuzzle="HANGMAN";
////        String newPuzzle="";
////        String newPuzzle="HAPPY DAY";
////        String newPuzzle="A FLOWER";
////        String newPuzzle="ON FIRE";
//        String newPuzzle="JAVA PROGRAM";
//        
//       
//        return newPuzzle;
//    }
    
    private void populatePuzzleList() {
         String list[] = {
            "MY FAIR LADY"
            ,"CRAZY EIGHTS"
            ,"BOOK WORM"
            ,"MY FUNNY VALENTINE"
            ,"FINANCIALLY SOUND PROOF"
            ,"GRANDMA'S FEATHER BED"
            ,"BUTTERSCOTCH COOKIES"
            ,"COMPUTER ANIMATION"
            ,"GARDEN TOOLS"
            ,"BALANCING ACT"
            ,"MAKE A MENTAL NOTE"
            ,"FIRING ON ALL CYLINDERS"
            
        };
        
        //add all the items to the List, using a List so we can removing items later
        for(String string:list) {
            puzzleList.add(string);
        }
        
    }
    
    public void displayPuzzle() {
        int i=0;
        if (currentPuzzle.length == 0) {
            System.out.println("Puzzle Not Found!");
            return;
        }
        for(i=0;i<currentPuzzle.length;i++) {
            if (currentPuzzle[i].isSpace) {
                System.out.print(" ");
            }
            else if (currentPuzzle[i].isVisible) {
                System.out.print(currentPuzzle[i].value);
            }
            else {
                System.out.print("?");
            }
        }
    }

    public void displayPuzzle2() {
        // alternate way of displaying the puzzle
        int puzzleRow=1; // which row we're working with, 2 rows total for now
        int colPosition=1; //which column, 12, 12
        int colCharStart=1; //which column to start on (to adjust for center)
        int charRow=1; //which row of the character build are we on, 3 total
        int numWords=1; // how many words are there in the puzzle
        
        String topBottom = "---|";
        String startLeft = "|";
        
        colCharStart = currentPuzzle.length >>> 1;
        for(Letter x: currentPuzzle) {
            System.out.print(startLeft);
            System.out.print(topBottom);
        }
        // next character line
        System.out.flush();
        colPosition=1;
        for(Letter x: currentPuzzle) {
            System.out.print(startLeft);
            if (colPosition==colCharStart) {
                System.out.print(" " + x.value + " ");
            }
            else {
                   System.out.print("   ");
            }
            
        }
        
        
    }
    
    public int countLetters(char letter){
        int count = 0;

        if(!Character.isLetter(letter) || this.puzzleText.trim().isEmpty()){
            System.out.print("Invalid letter or puzzle given!");
            return -1;
        }

        for (int i=0; i < this.puzzleText.length(); i++) {
            if (this.puzzleText.charAt(i) == letter) {
                if(!this.currentPuzzle[i].isVisible){
                    this.currentPuzzle[i].isVisible = true;
                    count++;
                }
            }
        }

        this.remainingLetters -= count; 

        return (byte) count;
    }    
    
    //moved from Game class ~dustin
    public void getPuzzleLength() {
        System.out.println("Puzzle is: " + puzzleText.length()
                + " characters long");
    }
    
    public void getPuzzleText() {
        System.out.println("Puzzle text is: '" + this.puzzleText + "'");
    }
    
    public void showRemainingVowels() {
        System.out.println("There are " + remainingVowels 
                + " vowels remaining.");
    }

    public int getNumberOfLettersGuessed(String guessedLetter) {
        
        int numberOfHits = 0;
        boolean isConsonant = false;
        String consonants[]= {"B","C","D","F","G","H","J","K","L","M","N","P"
                            ,"Q","R","S","T","V","W","X","Y","Z"};
        double dRatio = 0;  //percentage of letters correctly guessed, as double
        int iRatio = 0;     //percentage of letters correctlyi guessed, as int
        for (String validLetter : consonants) {
            String guess = guessedLetter.toUpperCase();
            if (validLetter.equals(guess)) {
                isConsonant=true;
            }
        }
        
        if (isConsonant == false)    {
            System.out.println(guessedLetter + " is not a consonant.\n"
                    + "Please enter a \"consonant\".");
            return 0;
        }
        
        int puzzleLengthWithoutLetter = 0;
        puzzleLengthWithoutLetter=puzzleText.replace(guessedLetter, "").length();
        numberOfHits=puzzleText.length() - puzzleLengthWithoutLetter;
        
        dRatio=(double)numberOfHits/(double)puzzleText.length();
        dRatio=dRatio*100;
        iRatio=(int)dRatio;
        System.out.println("Player guessed " + numberOfHits + " letters "
                + "correctly, " + iRatio + "% of the total.");
        return numberOfHits;
    }
}
