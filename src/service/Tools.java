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

    public Tools() {
    }

    /**
     * static method to return a random member from array
     *
     * @param keys
     * @return
     */
    public static String getRandomMember(String[] keys) {
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
     *
     * @param keys
     * @return x must be always > Integer.MIN_VALUE or if x == MIN_VALUE there
     * is no value to return
     */
    public static int getRandomMember(int[] keys) {
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

    /**
     * bind the url with the request parameters
     */
    public static String bindUrlwithParameters(String url, String parameters, String bind) {
        String res = "";
        if (bind != null && !bind.equals("")) {
            if (!url.endsWith(bind) && !parameters.startsWith(bind)) {
                res = url + bind + parameters;
            } else if ((url.endsWith(bind) && !parameters.startsWith(bind))
                    || (!url.endsWith(bind) && parameters.startsWith(bind))) {
                res = url + parameters;
            } else if (url.endsWith(bind) && parameters.startsWith(bind)) {
                res = url + parameters.substring(1);
            }
        } else {
            res = url + parameters;
        }

        return res;
    }
     public static String getMovieYear(String name) {
        String year = "Unknown";
        if (name.contains("(y")) {

            year = name.substring(name.indexOf("(y") + 2, name.indexOf("(y") + 6);
        }

        return year;
    }
}
