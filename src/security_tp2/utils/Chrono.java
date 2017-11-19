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
public class Chrono {
    private static long timeSinceLastStop = -1;
    
    public static long getTimeAndRestart(){
        long time = getTime();
        start();
        return time;
    }
    
    public static long getTime(){
        if(timeSinceLastStop > 0){
            return System.currentTimeMillis() - timeSinceLastStop;
        }
        return 0;
    }
    
    public static void start(){
        timeSinceLastStop = System.currentTimeMillis();
    }
    
}
