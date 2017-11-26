/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security_tp2.protocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;
import security_tp2.utils.AES;
import security_tp2.utils.Chrono;
import security_tp2.utils.HexUtils;
import security_tp2.utils.MemoryUtils;
import security_tp2.utils.PuzzleUtils;
import security_tp2.utils.Stats;

/**
 *
 * @author j-c9
 */
public class BaseProtocol implements Protocol{  
   

    @Override
    public Stats getStatsForProtocol(int puzzleLenght) {        
        Stats stats = new Stats(puzzleLenght);// For stats only
        Chrono.start();// For stats only
        MemoryUtils memoryUtils = new MemoryUtils(Runtime.getRuntime());// For stats only
        
        // --- GENERATION DES PUZZLES ----
        List<Pair<String,String>> puzzles = generatePuzzleList(puzzleLenght);
        stats.setPuzzleGenerationStats(Chrono.getTimeAndRestart(), memoryUtils.getUseMemory(Runtime.getRuntime()));
        
        // --- SOLVING ---
        Pair<Integer,String> solvedPuzzleResult = solvePuzzle(puzzles.get(PuzzleUtils.getRandomIndex(puzzles.size())));
        stats.setSolvePuzzleStats(Chrono.getTimeAndRestart(), memoryUtils.getUseMemory(Runtime.getRuntime()));
        
        // --- HACKING ---
        Pair<Integer,String> hackPuzzleResult = hackPuzzle(solvedPuzzleResult.getKey(), puzzles);
        stats.setHackingStats(Chrono.getTime(), memoryUtils.getUseMemory(Runtime.getRuntime()));
        
        return stats;
    }
    
    
    protected static Pair<Integer,String> hackPuzzle(int index, List<Pair<String,String>> puzzles){
        for(Pair<String,String> puzzle : puzzles){
            Pair<Integer,String> puzzleResult = solvePuzzle(puzzle);
            if(puzzleResult.getKey() == index){
                return puzzleResult;
            }
        }
        return null;
    }
    
    protected static Pair<Integer,String> solvePuzzle(Pair<String,String> pair){
        byte[] puzzleKey = PuzzleUtils.generatePuzzleKey(pair.getKey(), 1000);
        
        String solvedPuzzle = AES.decrypt(pair.getValue(), new String(puzzleKey));
        int startIndex = solvedPuzzle.length() - 36;
        return new Pair<>(Integer.valueOf(solvedPuzzle.substring(0,startIndex)),solvedPuzzle.substring(startIndex));
    }
    
    protected static List<Pair<String,String>> generatePuzzleList(int iteration){
        List<Pair<String,String>> lst = new ArrayList<>();
        for(int i = 0; i<= iteration; i++){
            String prePuzzleKey = PuzzleUtils.generateRandomKey();
            lst.add(new Pair<String,String>(
                    prePuzzleKey,
                    PuzzleUtils.generatePuzzle(i, prePuzzleKey ,PuzzleUtils.generateRandomKey())
            ));
        }
        
        Collections.shuffle(lst);
        return lst;
    
    }
    
}
