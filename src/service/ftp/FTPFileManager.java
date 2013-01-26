/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.ftp;

import service.ftp.domain.FTPMediaFile;
import service.filesystem.TypeX;
import service.ftp.FtpConnector;
import java.io.*;
import java.util.*;
import java.net.*;
import org.apache.commons.net.ftp.*;
import org.apache.commons.io.*;

/**
 *
 * @author kavan
 */
public class FTPFileManager {

    private String user, pass, ftpServ;
    private FtpConnector fc;
    private FTPFile[] root;
    private String[] categories = {"TvShows", "Movies", "Documentaries", "Movies-Persian"};
    private FTPMediaFile[] cateFolders;
    private FTPMediaFile homeFolder;

    public FTPFileManager() {//test constructor
        ftpServ = "solxiom.com";
        user = "mediacenter";
        pass = "45684568";
        fc = new FtpConnector(user, pass, ftpServ);
        fc.login();



//            System.out.println("Connection : " + fc.isConnected() );


        try {
            FTPFile homeFTP = null;
            FTPFile[] homeFTPFolder = fc.listFiles("disk1");
            for (int i = 0; i < homeFTPFolder.length; i++) {
                if (homeFTPFolder[i].getName().equalsIgnoreCase("Media")) {
                    homeFTP = homeFTPFolder[i];
                }
            }
            homeFolder = new FTPMediaFile(homeFTP, fc, "disk1", null);
            root = fc.listFiles(FtpConnector.HOME_ROOT);
            FTPMediaFile mf;
            cateFolders = new FTPMediaFile[categories.length];
            int counter = 0;

            for (int i = 0; i < root.length; i++) {
                mf = new FTPMediaFile(root[i], fc, FtpConnector.HOME_ROOT, homeFolder);

                if (mf.isMediaType(TypeX.CATEGORY_DIR) && isCategoryFolder(mf.getName())) {

                    cateFolders[counter] = mf;
                    counter++;
                }


            }

        } catch (IOException e) {
            //System.out.println("Name : "+ e);
        }




    }

    public FTPFileManager(String user, String pass, String serverIp, int port) {//test constructor
        this.ftpServ = serverIp;
        this.user = user;
        this.pass = pass;
        fc = new FtpConnector(this.user, this.pass, this.ftpServ, port);
        fc.login();



        //System.out.println("Connection : " + fc.isConnected() );

        try {
            FTPFile homeFTP = null;
            FTPFile[] homeFTPFolder = fc.listFiles("disk1");
            for (int i = 0; i < homeFTPFolder.length; i++) {
                if (homeFTPFolder[i].getName().equalsIgnoreCase("Media")) {
                    homeFTP = homeFTPFolder[i];
                }
            }
            homeFolder = new FTPMediaFile(homeFTP, fc, "disk1", null);
            if (!fc.isConnected()) {
                fc.login();
            }
            root = fc.listFiles(FtpConnector.HOME_ROOT);
            FTPMediaFile mf;
            cateFolders = new FTPMediaFile[categories.length];
            int counter = 0;
            for (int i = 0; i < root.length; i++) {
                mf = new FTPMediaFile(root[i], fc, FtpConnector.HOME_ROOT, homeFolder);
                if (mf.isMediaType(TypeX.CATEGORY_DIR) && isCategoryFolder(mf.getName())) {
                    cateFolders[counter] = mf;
                    counter++;
                }


            }

        } catch (IOException e) {
            //System.out.println("Name : "+ e);
        }




    }

    public boolean isCategoryFolder(String fileName) {

        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equalsIgnoreCase(fileName)) {
                return true;
            }
        }
        return false;
    }

    public FTPMediaFile[] listCategories() {

        return this.cateFolders;
    }
    public FTPMediaFile[] removeProhibitedFoldersFromRoot(FTPMediaFile[] root){
        List<FTPMediaFile> result = new ArrayList<FTPMediaFile>();
        
        for(FTPMediaFile f: root){
            if(isCategoryFolder(f.getName()))
                result.add(f);
        }
        
        return result.toArray(new FTPMediaFile[result.size()]);
    }
    
}
