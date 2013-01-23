/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filesystem;

import GUI.MainFrame;
import control.Controller;
import java.util.Scanner;

/**
 *
 * @author kavan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FTPFileManager setup = new FTPFileManager();
        Scanner sc = new Scanner(System.in);
        FTPMediaFile[] fold = setup.listCategories();
        int luku = 0;
        FTPMediaFile currentFolder = null;
        boolean infoAsked = false;
        //System.out.println(new LocalFileManager().makeDir("Downloads"));
        //System.out.println(System.getProperty("user.dir")+"/Tools/VLC/vlc.exe");
//        new MainFrame();
        Process p = null;

        while (true) {
            infoAsked = false;
            if (currentFolder != null && currentFolder.getParentFolder() != null) {
                System.out.println(" path: " + currentFolder.getFilePath());
                System.out.println(-1 + "  \\..");
            } else {
                System.out.println(" path: disk1/Media");
            }
            int i = 0;

//            System.out.println("folder length " + fold.length);
            for (FTPMediaFile mf : fold) {
                //System.out.println(""+fold.length);
                if ((mf.isMediaType(TypeX.CATEGORY_DIR))
                        && setup.isCategoryFolder(mf.getName())
                        || (mf.isMediaType(TypeX.MEDIA_DIR) || !mf.isDirectory())) {
                  
                        System.out.println(" " + i + " - " + mf.getMediaType() + " : " + mf.getMediaName());

                    


                }
                i++;
            }

            System.out.println("use a number as input for files and folders : ");
            String par = sc.nextLine();
            if (par.equalsIgnoreCase("exit")) {
                System.out.println("bye..");
                break;
            }
            if (par.equalsIgnoreCase("c") && p != null) {
                p.destroy();
            } else {

                if (par.endsWith("-i")) {
                    String[] argsx = par.split(" ");
                    par = argsx[0];
                    infoAsked = true;

                }

                try {

                    luku = Integer.parseInt(par);
                } catch (Exception e) {
                    System.out.println("Bad input: ");
                    luku = -2;
                    
                }
                try {

                    if (luku != -1 && fold[luku].getFTPFile().isDirectory() && fold[luku].getMediaType().equals(TypeX.CATEGORY_DIR)) {

                        //System.out.println(fold[luku].getFilePath());


                        currentFolder = fold[luku];
                        fold = fold[luku].listFTPMediaFiles();



                    } else if (luku == -1) {
                        //if(currentFolder.getParentFolder() == null)
                        //  System.out.println("You are in Home folder!!");
                        if (currentFolder != null && currentFolder.getParentFolder() != null) {

                            fold = currentFolder.getParentFolder().listFTPMediaFiles();
                            currentFolder = currentFolder.getParentFolder();
                            //System.out.println(" parent " + currentFolder.getParentFolder().getMediaName() );
                        } else {
                            System.out.println(" This is your root folder!!");
                        }


                    } else if (fold[luku].getMediaType().equals(TypeX.MEDIA_DIR)
                            || fold[luku].getMediaType().equals(TypeX.VIDEO)
                            || fold[luku].getMediaType().equals(TypeX.AUDIO)
                            || fold[luku].getMediaType().equals(TypeX.IMAGE)) {
                        
                        if (infoAsked) {
                            System.out.println(""+ new Controller().getMovieInfo(fold[luku]));
                        } else {
                            if (p != null) {
                                p.destroy();
                            }
                            System.out.println("Opening " + fold[luku].getMediaType() + " file...");
                            p = new Controller().openMediaFile(fold[luku]);
                        }
                        currentFolder = fold[luku];
                        fold = new FTPMediaFile[]{fold[luku]};
                        // p.destroy();
                    } else {
                        System.out.println("ttt " + fold[luku].getMediaType() + " " + fold[luku].getMediaName());
                        System.out.println("Sorry Can't open the requested item!");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No such File or Directory");
                }

            }



        }

//        Controller co = new Controller();
//        System.out.println("Opening " + System.getProperty("user.dir"));




        // TODO code application logic here
    }
}
