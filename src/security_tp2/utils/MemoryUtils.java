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
public class MemoryUtils {
    Runtime rt;
    private static long prevTotal ;
    private static long prevFree;

    public MemoryUtils(Runtime rt) {
        this.rt = rt;
        this.prevTotal = 0;
        this.prevFree = rt.freeMemory();
    }
    
    private long getPreviousMemoryUsed(){
        return prevTotal - prevFree;
    }
    
    public static String humanReadableByteCount(long bytes) {
    int unit = 1000;
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    return String.format("%.1f", bytes / Math.pow(unit, exp));
}
    
    public long getUseMemory(Runtime rt){
        return rt.totalMemory() - rt.freeMemory();
    }
    
    


    
}
