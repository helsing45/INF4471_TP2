/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package security_tp2;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import security_tp2.utils.AES;
import security_tp2.utils.Chrono;
import security_tp2.utils.HexUtils;
import security_tp2.utils.Stats;
import security_tp2.utils.StringUtils;

/**
 *
 * @author j-c9
 */
public class Security_Tp2 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for(int i=0; i <= 100; i++){
            Stats stats = generateStats(10);
            System.out.println(stats.toString());
        }
        /*for(int i=0; i <= 1000; i++){
            Stats stats = generateStats(100);
            System.out.println(stats.toString());
        }
        for(int i=0; i <= 1000; i++){
            Stats stats = generateStats(1000);
            System.out.println(stats.toString());
        }*/
    }
    
    protected static Stats generateStats(int puzzleLenght){
        Stats stats = new Stats(puzzleLenght);
        Chrono.start();
        List<Pair<String,String>> puzzles = generatePuzzleList(puzzleLenght);
        Runtime rt = Runtime.getRuntime();
        long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
        System.out.println("UsedMD: "+usedMB);
        stats.setGeneratePuzzleTime(Chrono.getTimeAndRestart());
        Pair<Integer,String> solvedPuzzleResult = solvePuzzle(puzzles.get(getRandomIndex(puzzles.size())));
        stats.setSolvePuzzleTime(Chrono.getTimeAndRestart());
        Pair<Integer,String> hackPuzzleResult = solvePuzzle(solvedPuzzleResult.getKey(), puzzles);
        stats.setHackingTime(Chrono.getTime());
        return stats;
    }
    
    protected static Pair<Integer,String> solvePuzzle(int index, List<Pair<String,String>> puzzles){
        for(Pair<String,String> puzzle : puzzles){
            Pair<Integer,String> puzzleResult = solvePuzzle(puzzle);
            if(puzzleResult.getKey() == index){
                return puzzleResult;
            }
        }
        return null;
    }
    
    protected static Pair<Integer,String> solvePuzzle(Pair<String,String> pair){
        byte[] puzzleKey = generatePuzzleKey(pair.getKey(), 1000);
        
        String solvedPuzzle = AES.decrypt(pair.getValue(), new String(puzzleKey));
        int startIndex = solvedPuzzle.length() - 32;
        return new Pair<>(Integer.valueOf(solvedPuzzle.substring(0,startIndex)),solvedPuzzle.substring(startIndex));
    }
    
    protected static List<Pair<String,String>> generatePuzzleList(int iteration){
        List<Pair<String,String>> lst = new ArrayList<>();
        for(int i = 0; i<= iteration; i++){
            String prePuzzleKey = generateRandomKey();
            lst.add(new Pair<String,String>(prePuzzleKey,generatePuzzle(i, prePuzzleKey)));
        }
        
        Collections.shuffle(lst);
        return lst;
    }
    
    protected static String generatePuzzle(int puzzleIndex, String prePuzzleKey){
        byte[] secretKey = generatePuzzleKey(prePuzzleKey, 1000);
        String indexMessage = puzzleIndex + HexUtils.byteArrayToHexString(secretKey);
        return AES.encrypt(indexMessage, new String(secretKey));
    }
    
    protected static byte[] generatePuzzleKey(String initialKey,int iteration){
        byte[] key = StringUtils.getBytesFromString(initialKey);
        for(int i=0; i <= iteration; i++){
            key = StringUtils.getMD5(key);
        }
        return key;
    }
    
    protected static String generateRandomKey(){
        UUID uuid = UUID.randomUUID() ;
        return uuid.toString();
    }
    
    protected static int getRandomIndex(int max){
        Random r = new Random();
        int Low = 1;
        return r.nextInt(max-Low) + Low;
    }
    
    
}
