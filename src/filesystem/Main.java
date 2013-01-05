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
        int luku =0;
        FTPMediaFile currentFolder=null;
        //System.out.println(new LocalFileManager().makeDir("Downloads"));
         //System.out.println(System.getProperty("user.dir")+"/Tools/VLC/vlc.exe");
        new MainFrame();
        Process p = null;
       
        while(true){
          
            if(currentFolder != null && currentFolder.getParentFolder() != null)
            {
              System.out.println(" path: " + currentFolder.getFilePath());
              System.out.println(-1 + "  \\..");
            }
            else
                System.out.println(" path: disk1/Media" );
            int i = 0;
            
//            System.out.println("folder length " + fold.length);
            for(FTPMediaFile mf : fold)
            {
                //System.out.println(""+fold.length);
                System.out.println(" "+ i+" - " + mf.getMediaType()+" : "+mf.getName());
                
                i++;
            }

            System.out.println("anna numero : ");
            String par = sc.nextLine();
            if(par.equalsIgnoreCase("exit")){
                System.out.println("bye..");
                break;
            }
            if(par.equalsIgnoreCase("c") && p != null)
                p.destroy();
            else
                try{
                     
                     luku = Integer.parseInt(par);
                   
                    if(luku != -1 && fold[luku].getFTPFile().isDirectory())
                    {
                       
                            //System.out.println(fold[luku].getFilePath());
                        
                       
                                currentFolder = fold[luku];
                                fold = fold[luku].listFTPMediaFiles();
                        
                                
                                
                    }
                    else if(luku == -1){
                         //if(currentFolder.getParentFolder() == null)
                           //  System.out.println("You are in Home folder!!");
                         if(currentFolder != null && currentFolder.getParentFolder() != null)
                         {

                               fold = currentFolder.getParentFolder().listFTPMediaFiles();
                               currentFolder = currentFolder.getParentFolder();
                               //System.out.println(" parent " + currentFolder.getParentFolder().getMediaName() );
                        }
                         else
                             System.out.println(" This is your root folder!!" );
                         
                             
                    }

                    else{
                        System.out.println("Opening "+fold[luku].getMediaType()+" file...");
                        
                        p = new Controller().openMediaFile(fold[luku]);
                        
                       // p.destroy();
                    }
            }catch(Exception e){
                           System.out.println(  e);
                           break;
            }


        }

        Controller co = new Controller();
        System.out.println("Opening "+ System.getProperty("user.dir") );
              
     


        // TODO code application logic here
    }
}
