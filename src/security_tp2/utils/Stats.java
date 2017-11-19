/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package security_tp2.utils;

/**
 *
 * @author j-c9
 */
public class Stats {
    private int puzzleListLenght;
    private long generatePuzzleTime;
    private long solvePuzzleTime;
    private long hackingTime;
    
    public Stats(int puzzleListLenght) {
        this.puzzleListLenght = puzzleListLenght;
    }  
    
    public long getGeneratePuzzleTime() {
        return generatePuzzleTime;
    }
    
    public void setGeneratePuzzleTime(long generatePuzzleTime) {
        this.generatePuzzleTime = generatePuzzleTime;
    }
    
    public long getSolvePuzzleTime() {
        return solvePuzzleTime;
    }
    
    public void setSolvePuzzleTime(long solvePuzzleTime) {
        this.solvePuzzleTime = solvePuzzleTime;
    }
    
    public long getHackingTime() {
        return hackingTime;
    }
    
    public void setHackingTime(long hackingTime) {
        this.hackingTime = hackingTime;
    }

    @Override
    public String toString() {
        return "puzzleListLenght: " + puzzleListLenght+" generate Time: " + generatePuzzleTime + " solvingTime: "+ solvePuzzleTime +" hackingTime: " + hackingTime;
    }
    
    
    
}
