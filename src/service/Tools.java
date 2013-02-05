/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author kavan
 */
public class Tools {
    
    public Tools(){
        
    }
      /**
     * static method to return a random member from array
     * @param keys
     * @return 
     */
    
     public static String getRandomMember(String[] keys){
        if (keys.length == 0) {
            return "no-key";
        }
        int index = 0;
        index = (int) (Math.random() * (keys.length));
        if (index == keys.length && keys.length > 0) {
            index -= 1;
        }


        return keys[index];
    }
     /**
     * static method to return a random member from array
     * @param keys
     * @return x must be always > Integer.MIN_VALUE or if x == MIN_VALUE there is no value to return 
     */
     public static int getRandomMember(int[] keys){
        if (keys.length == 0) {
            return Integer.MIN_VALUE;
        }
        int index = 0;
        index = (int) (Math.random() * (keys.length));
        if (index == keys.length && keys.length > 0) {
            index -= 1;
        }


        return keys[index];
    }
}
