/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rectangleoffortune;

/**
 *
 * @author Joseph/Dustin
 */
public class Puzzle {
    public static void main(String[] args) {
        // TODO code application logic here
        String puzzleString = "Hello world!";
        
        Puzzle puzzle = new Puzzle();
        puzzle.displayPuzzle(puzzleString);
    }
    
    public void displayPuzzle(String puzzleString){
        System.out.print(puzzleString);
    }
}