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
    private long generatePuzzleMemory;
    private long solvePuzzleMemory;
    private long hackingMemory;
    
    public Stats(int puzzleListLenght) {
        this.puzzleListLenght = puzzleListLenght;
    }
    
    public void setPuzzleGenerationStats(long time, long memoryUsed){
        this.generatePuzzleTime = time;
        this.generatePuzzleMemory = memoryUsed;
    }
    
    public void setSolvePuzzleStats(long time, long memoryUsed){
        this.solvePuzzleTime = time;
        this.solvePuzzleMemory = memoryUsed;
    }
    
    public void setHackingStats(long time, long memoryUsed){
        this.hackingTime = time;
        this.hackingMemory = memoryUsed;
    }
    
    

    @Override
    public String toString() {
        return puzzleListLenght +";"
                + generatePuzzleTime + ";" 
                + MemoryUtils.humanReadableByteCount(generatePuzzleMemory) +";"
                + solvePuzzleTime +";"
                + MemoryUtils.humanReadableByteCount(solvePuzzleMemory)+";"
                + hackingTime+";"
                + MemoryUtils.humanReadableByteCount(hackingMemory)+";";
    }
    
    
    
}
