/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.filesystem;

import java.io.*;
import java.util.*;

/**
 *
 * @author kavan
 */
public class LocalFileManager {

    public LocalFileManager() {
    }

    public String getCurrentDir() {

        return System.getProperty("user.dir");
    }

    public boolean makeDir(String name) {
        File f = new File(name);

        return f.mkdir();
    }

    public boolean makeDir(String name, String path) {

        return new File(path + "/" + name).mkdirs();
    }
}
