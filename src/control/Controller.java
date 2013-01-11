/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.*;
import filesystem.*;
import imdb.domain.DataObject;
import imdb.domain.TitleSearchOptions;
import imdb.service.DataService;
import imdb.service.DataServiceImpl;
import java.util.*;

/**
 *
 * @author kavan
 */
public class Controller {

    private File[] roots;
    private Runtime rt;

    public Controller() {
        roots = File.listRoots();
        rt = Runtime.getRuntime();
    }

    public Process openMediaFile(MediaFile mf) {
        OS_dedector os = new OS_dedector();
        if (os.isWindows()) {
            return openMediaFile_inWindos(mf);
        }
        if (os.isUnix()) {
            return openMediaFile_inLinux(mf);
        } else {
            return null;
        }
    }

    public String getMovieInfo(MediaFile mf) {

        String year = getMovieYear(mf.getName());
        DataObject info;
        TitleSearchOptions options;
        DataService service = new DataServiceImpl();
        if (year != null) {
            options = new TitleSearchOptions(mf.getMediaName(), year);
        } else {
            options = new TitleSearchOptions(mf.getName());
        }
//        options.setPlot("simple");
        info = service.getDataByTitle("http://imdbapi.org", options);
        return parseMovieInfo(info);
    }

    private String parseMovieInfo(DataObject info) {
        String str = "\n**************   *I**N**F**O*   *****************\n\n";
        str += "\n Name: " + info.getTitle() + " [" + info.getYear() + "]";
        str += "\n Duration: " + info.getRuntime() + " Rating: [" + info.getRating() + "/10] from " + info.getRating_count() +" users";
        str += "\n Language: " + info.getLanguage();
        str += "\n Genres: " + info.getGenres();
        str += "\n Plot: " + parseMoviePlot(info.getPlot());
        str += "\n Directors: " + info.getDirectors();
        str += "\n Actores: " + trimMovieActors(info.getActors(), 5);
        str += "\n \n \n**************   ******   **********************";

        return str;
    }

    private List<String> trimMovieActors(List<String> acts, int trim) {

        if (trim >= acts.size()) {
            return acts;
        }

        return acts.subList(0, trim);

    }

    private String parseMoviePlot(String plot) {
        String str = "";
        if (plot != null) {
            String[] ar = plot.split(" ");
            for (int i = 0; i < ar.length; i++) {
                str += ar[i] + " ";
                if (i > 10 && (i % 14 == 0)) {
                    str += "\n       ";
                }
            }
        }
        return str;
    }

    private String getMovieYear(String name) {
        String year = null;
        if (name.contains("(y")) {

            year = name.substring(name.indexOf("(y") + 2, name.indexOf("(y") + 6);
        }

        return year;
    }

    private Process openMediaFile_inLinux(MediaFile mf) {
        ProcessBuilder p;
        List<String> path = new ArrayList<String>();
        path.add("vlc");

        if (mf.isMediaType(TypeX.MEDIA_DIR) && mf.isDirContainsType(TypeX.VIDEO))//open videos in queue
        {
            MediaFile[] medFold = mf.listMediaFiles();

            for (MediaFile f : medFold) {

                if (f.isMediaType(TypeX.VIDEO)) {
                    path.add(f.getAbsoluteFilePath());
                }

            }
        } else if (mf.isMediaType(TypeX.VIDEO)) {
            path.add(mf.getAbsoluteFilePath());
        }

        if (mf.isMediaType(TypeX.MEDIA_DIR)
                && !mf.isDirContainsType(TypeX.VIDEO)) {
            System.out.println("******Can't open none video files****");
            for (MediaFile f : mf.listMediaFiles()) {

                System.out.println("" + f.getName());

            }
            return null;
        } else if (!mf.isMediaType(TypeX.MEDIA_DIR) && !mf.isMediaType(TypeX.VIDEO)) {
            System.out.println("******Can't open none video files****");
            System.out.println("" + mf.getName());
            return null;
        }



        p = new ProcessBuilder(path);

        try {

            return p.start();
        } catch (IOException e) {
            System.out.println("Runtime error : " + e);
            return null;
        }

    }

    private Process openMediaFile_inWindos(MediaFile mf) {
        //System.out.println("is folder empty ? " + mf.isDirEmpty());
        String param = "C:/Program Files (x86)/VideoLan/VLC/vlc.exe";//not work correctly and start downloading file instead
        String param2 = "C:/temp/VLC/vlc.exe";
        String param3 = System.getProperty("user.dir") + "/Tools/VLC/vlc.exe";
        if (mf.isMediaType(TypeX.VIDEO)) {

            //+ pathCorrector(getVLCPath())+
            String[] cmd = {"cmd.exe", "/C", "start", param3, mf.getAbsoluteFilePath()};
            try {

                return rt.exec(cmd);


            } catch (Exception e) {
                System.out.println("Runtime error : " + e);
                return null;
            }
        }
        if (mf.isMediaType(TypeX.MEDIA_DIR) && mf.isDirContainsType(TypeX.VIDEO))//open videos in queue
        {
            MediaFile[] medFold = mf.listMediaFiles();

            String[] cmd = {"cmd.exe", "/C", "start", param3};
            for (MediaFile f : medFold) {

                if (f.isMediaType(TypeX.VIDEO)) {
                    String[] cmd2 = new String[cmd.length + 1];
                    System.arraycopy(cmd, 0, cmd2, 0, cmd.length);
                    cmd2[cmd2.length - 1] = f.getAbsoluteFilePath();
                    cmd = cmd2;
                }
            }
            try {

                return rt.exec(cmd);


            } catch (Exception e) {
                System.out.println("Runtime error : " + e);
                return null;
            }


        }
        return null;
    }

    public String pathCorrector(String path) {

        String str = path;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\\') {
                str = str.substring(0, i) + "/" + str.substring(i + 1);
            }
        }
        return str;
    }

    public String getVLCPath() {
        String path = "";

        File vlsDir = null;
        for (int i = 0; i < roots.length; i++) {
            File f1 = new File(roots[i] + "Program Files/VideoLan/VLC");
            File f2 = new File(roots[i] + "Program Files (x86)/VideoLan/VLC");

            if (f1.exists() || f2.exists()) {
                if (f1.exists()) {
                    path = f1.getAbsolutePath();
                } else {
                    path = f2.getAbsolutePath();
                }

                break;
            }
        }
        //path+="\\vlc.exe";
        return path;
    }

    public String getFixedString(String input) {

        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isUpperCase(input.charAt(i)) || input.charAt(i) == ' ' || Character.isDigit(input.charAt(i))) {
                count++;
            }
        }
        if (count == input.length()) {
            return input;// if every letter in String is upper case then probebly is ment to be :)
        }

        String output = input + "Trim";
        /*
         why adding word Trim in to end of the string? for some stupid reason
         last word of the String will be lost in this code, and I don't have enough time to find this problem
         (maybe later i look what is wrong with it)
         * for now after adding word Trim this code work fine :)

         */

        ArrayList<String> list = new ArrayList<String>();
        String word = "";
        for (int i = 0; i < output.length(); i++) {
            if (i > 0) {
                if (Character.isUpperCase(output.charAt(i))
                        || (output.charAt(i) == '_')
                        || (output.charAt(i) == '-')
                        || (output.charAt(i) == '.')
                        || (output.charAt(i) == '&'
                        && (output.charAt(i - 1) != ' ' || output.charAt(i + 1) != ' '))
                        || (Character.isDigit(output.charAt(i))
                        && (!Character.isDigit(output.charAt(i - 1))
                        && !Character.isDigit(output.charAt(i - 1))))) {
                    list.add(word);
                    if (Character.isUpperCase(output.charAt(i))
                            || (Character.isDigit(output.charAt(i)))) {
                        word = "" + output.charAt(i);
                    } else {
                        word = "";
                    }
                    if (output.charAt(i) == '&') {
                        list.add("&");
                    }
                } else {
                    word += output.charAt(i);
                }
            } else {
                word += output.charAt(i);
            }
        }
        output = "";
        // System.out.println("the size of the list :" + list.size());
        try {
            for (String tex : list) {
                if (tex.length() >= 1) {
                    tex = tex.trim();
                    output += tex.substring(0, 1).toUpperCase() + tex.substring(1).toLowerCase() + " ";
                }
            }
        } catch (Exception ex) {
        }

        return output.trim();

    }
    /*
    
     * 
     */
}
