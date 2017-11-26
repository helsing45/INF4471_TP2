/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package security_tp2.utils;

import java.util.Random;
import java.util.UUID;
import javafx.util.Pair;

public class PuzzleUtils {
    
    
    public static String generateRandomKey(){
        UUID uuid = UUID.randomUUID() ;
        return uuid.toString();
    }
    
    public static int getRandomIndex(int max){
        Random r = new Random();
        int Low = 1;
        return r.nextInt(max-Low) + Low;
    }
    
    public static byte[] generatePuzzleKey(String initialKey,int iteration){
        byte[] key = StringUtils.getBytesFromString(initialKey);
        for(int i=0; i <= iteration; i++){
            key = StringUtils.getMD5(key);
        }
        return key;
    }
    
    public static String generatePuzzle(int puzzleIndex, String prePuzzleKey,String secretKey){
        byte[] puzzleKey = PuzzleUtils.generatePuzzleKey(prePuzzleKey, 1000);
        String indexMessage = puzzleIndex + secretKey;
        return AES.encrypt(indexMessage, new String(puzzleKey));
    }
}
