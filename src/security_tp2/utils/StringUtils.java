/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security_tp2.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import security_tp2.Security_Tp2;

/**
 *
 * @author j-c9
 */
public class StringUtils {
    
    public static byte[] getBytesFromString(String string){
        byte[] bytesOfMessage;
        try {
            bytesOfMessage = string.getBytes("UTF-8");            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StringUtils.class.getName()).log(Level.SEVERE, null, ex);
            return new byte[0];
        }
        return bytesOfMessage;
    }
    
    public static String getStringFromByte(byte[] bytes){
        return new String(bytes);
    }
    
     public static byte[] getMD5(byte[] bytesOfMessage){
        MessageDigest md;
        try {      
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StringUtils.class.getName()).log(Level.SEVERE, null, ex);
            return new byte[0];
        }
        return md.digest(bytesOfMessage);
    }
}
