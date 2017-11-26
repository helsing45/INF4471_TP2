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
import security_tp2.protocol.AdvanceProtocol;
import security_tp2.protocol.BaseProtocol;
import security_tp2.protocol.Protocol;
import security_tp2.utils.AES;
import security_tp2.utils.Chrono;
import security_tp2.utils.HexUtils;
import security_tp2.utils.MemoryUtils;
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
        int iteration = 0;
        long startTime = System.currentTimeMillis();
        long timeLimit = 1000 * 60 * 60 * 5;
        
        System.out.println("Puzzle list length;puzzle generation time (mSec);memory usage (generation);Solving time (mSec);memory usage (solving);hacking time (mSec);memory usage (hacking)");
        while(iteration <= 50 && ((System.currentTimeMillis() - startTime) < timeLimit)){
            System.out.println(getProtocol().getStatsForProtocol(100));
            System.out.println(getProtocol().getStatsForProtocol(1000));
            System.out.println(getProtocol().getStatsForProtocol(10000));
            System.out.println(getProtocol().getStatsForProtocol(100000));
            System.out.println("iteration: " + iteration);
            iteration ++;
        }
    }
    
    protected static Protocol getProtocol(){
        return new AdvanceProtocol();
    }
    
}
