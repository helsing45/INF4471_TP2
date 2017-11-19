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
public class HexUtils {
    private final static char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	  /**
          * Convert a byte[] array to readable string format. This makes the "hex" readable!
          * @return result String buffer in String format
          * @param in byte[] buffer to convert to string format
          */
	    public static String byteArrayToHexString(byte in[]) {
	        byte ch = 0x00;
 
	        if (in == null || in.length <= 0) {
	            return "";
	        }
 
	        StringBuffer out = new StringBuffer(in.length * 2);
 
	        for (int i = 0; i < in.length; i++) {
	            ch = (byte) (in[i] & 0xF0); // Strip off high nibble
	            ch = (byte) (ch >>> 4); // shift the bits down
	            ch = (byte) (ch & 0x0F); // must do this is high order bit is on!
 
	            out.append(CHARS[ (int) ch]); // convert the nibble to a String Character
	            ch = (byte) (in[i] & 0x0F); // Strip off low nibble
	            out.append(CHARS[ (int) ch]); // convert the nibble to a String Character
	        }
 
	        return out.toString();
	    }	 
            
            
}
