/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package security_tp2.protocol;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import security_tp2.utils.AES;
import security_tp2.utils.Chrono;
import security_tp2.utils.HexUtils;
import security_tp2.utils.MemoryUtils;
import security_tp2.utils.PuzzleUtils;
import security_tp2.utils.Stats;

public class AdvanceProtocol implements Protocol{
    
    @Override
    public Stats getStatsForProtocol(int puzzleLenght) {
        Stats stats = new Stats(puzzleLenght);
        Chrono.start();
        MemoryUtils memoryUtils = new MemoryUtils(Runtime.getRuntime());
        
        // --- GENERATION DES PUZZLES ----
        List<String> prePuzzleKeyLst = new ArrayList<>();
        List<String> puzzles = new ArrayList<>();
        for(int i = 0; i<= puzzleLenght; i++){
            String prePuzzleKey = PuzzleUtils.generateRandomKey();
            prePuzzleKeyLst.add(prePuzzleKey);
            puzzles.add(PuzzleUtils.generatePuzzle(i,prePuzzleKey,PuzzleUtils.generateRandomKey()));
        }
        stats.setPuzzleGenerationStats(Chrono.getTimeAndRestart(), memoryUtils.getUseMemory(Runtime.getRuntime()));
        
        // --- SOLVING ---
        List<byte[]> puzzleKeys = new ArrayList<>();
        for(String prePuzzleKey: prePuzzleKeyLst){
            puzzleKeys.add(PuzzleUtils.generatePuzzleKey(prePuzzleKey, 1000));
        }
        Pair<Integer,String> solvedPuzzle= null;
        String puzzle = puzzles.get(PuzzleUtils.getRandomIndex(puzzleLenght));
        for(byte[] puzzleKey : puzzleKeys){
            solvedPuzzle = solvePuzzle(puzzleKey, puzzle);
            if(solvedPuzzle != null){
                break;
            }
        }
        int chooseIndex = -1;
        if(solvedPuzzle != null){
            chooseIndex = solvedPuzzle.getKey();
        }
        
        stats.setSolvePuzzleStats(Chrono.getTimeAndRestart(), memoryUtils.getUseMemory(Runtime.getRuntime()));
        
        // --- HACKING ---
        List<byte[]> hackerPuzzleKeys = new ArrayList<>();
        for(String prePuzzleKey: prePuzzleKeyLst){
            hackerPuzzleKeys.add(PuzzleUtils.generatePuzzleKey(prePuzzleKey, 1000));
        }
        
        Pair<Integer,String> hackerSolvedPuzzle = null;
        outerloop:
        for(String p:puzzles){
            for(byte[] hackPuzzleKey : hackerPuzzleKeys){
                hackerSolvedPuzzle = solvePuzzle(hackPuzzleKey, p);
                if(hackerSolvedPuzzle != null && hackerSolvedPuzzle.getKey() == chooseIndex){
                    break outerloop;
                }
            }
            
        }
        stats.setHackingStats(Chrono.getTimeAndRestart(), memoryUtils.getUseMemory(Runtime.getRuntime()));
        
        return stats;
    }
    
    protected static Pair<Integer,String> solvePuzzle(byte[] puzzleKey, String puzzle){
        
        String solvedPuzzle = AES.decrypt(puzzle, new String(puzzleKey));
        if(solvedPuzzle == null){
            return null;
        }
        int startIndex = solvedPuzzle.length() - 36;
        try{
            return new Pair<>(Integer.valueOf(solvedPuzzle.substring(0,startIndex)),solvedPuzzle.substring(startIndex));
        }catch(Exception ex){
            return null;
        }
    }
    
}
