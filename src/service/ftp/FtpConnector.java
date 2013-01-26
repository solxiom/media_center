/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.ftp;

import org.apache.commons.net.ftp.*;
import org.apache.commons.io.*;

/**
 *
 * @author kavan
 */
public class FtpConnector extends FTPClient{

    private String user ;
    private String password;
    private String ftpServer;
    private  int port = 21;
     public final static String HOME_ROOT ="disk1/Media";

    public FtpConnector(String uName, String pass, String ftpServer){
       this.user = uName;
       this.password = pass;
       this.ftpServer = ftpServer;
    }
    public FtpConnector(String uName, String pass, String ftpServer,int port){
       this.user = uName;
       this.password = pass;
       this.ftpServer = ftpServer;
       this.port = port;
    }

   public boolean login(){

       try{
           super.connect(ftpServer,port);
          return super.login(user, password);
       }
       catch(Exception e){
           System.out.println(e);
           return false;
       }
   }
   public String getFTPServerer(){
       return this.ftpServer;
   }
   public String getAbsoluteLoginPath(){
       return "ftp://"+user+":"+password+"@"+getFTPServerer()+":"+port;
   }



}
