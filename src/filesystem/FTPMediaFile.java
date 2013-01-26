/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filesystem;

import java.io.*;
import java.util.*;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author kavan
 */
public class FTPMediaFile implements MediaFile {

    private filesystem.TypeX type = TypeX.UNKNOWN;
    private filesystem.QualityX quality = QualityX.UNKNOWN;
    String videoTypes = "";
    String settingFilesFolder;
    private String fileFormat = "";
    private String mediaName = "";
    private String qualityStr = "";
    private FTPFile file;
    private FTPMediaFile[] mediaFiles;
    private FTPMediaFile parent;
    private FtpConnector fc;
    private final String FILEPATH;
    private boolean emptyDir;

    public FTPMediaFile(FTPFile f, FtpConnector con, String path, FTPMediaFile parent) {


        this.file = f;
        this.fc = con;
        this.parent = parent;
        setMediaType();
        this.mediaName = this.getFixedName();
        setQuality();
        FILEPATH = path + "/" + file.getName();
        emptyDir = false;

        //type = TypeX.UNKNOWN;
        //this.listFTPMediaFiles();


    }

    private void setMediaFileList() {
        FTPFile[] dir;
        if (this.file.isDirectory()) {
            try {
                if (!fc.isConnected()) {
                    fc.login();
                }
                dir = this.fc.listFiles(FILEPATH);
                emptyDir = dir.length > 0;
                mediaFiles = new FTPMediaFile[dir.length];

                for (int i = 0; i < dir.length; i++) {
                    mediaFiles[i] = new FTPMediaFile((FTPFile) dir[i], this.fc, FILEPATH, this);
                }

            } catch (Exception e) {
                //System.out.println(e);
            }
        }


    }

    public FTPMediaFile[] listFTPMediaFiles() {
        this.setMediaFileList();
        return this.mediaFiles;
    }

    public MediaFile[] listMediaFiles() {
        return this.listFTPMediaFiles();
    }

    public String getMediaName() {
        return new control.CMD_Controller().getFixedString(this.getFixedName());
    }

    private String fixName() {
        String nameStr = "";


        String[] words = this.file.getName().split("\\.");
        if (!this.file.isDirectory()) {
            for (int i = 0; i < words.length - 1; i++)// why lenght-1 ? last word will be the file format like .avi
            {
                nameStr += words[i] + " ";
            }
        } else {
            for (String w : words) {
                nameStr += w + " ";
            }
        }

        nameStr = nameStr.substring(0, 1).toUpperCase() + nameStr.substring(1);


        if (this.getMediaType() == TypeX.VIDEO || this.getMediaType() == TypeX.MEDIA_DIR) {
            try {
                return nameStr.split("\\(")[0];//
            } catch (Exception e) {
                return nameStr;
            }
        } else {
            return nameStr;
        }
    }

    public String getFixedName() {
        return this.fixName();
    }

    private void setQuality() {

        String str = this.file.getName().split("\\.")[0];

        this.quality = QualityX.UNKNOWN;

        if (this.getMediaType() == TypeX.VIDEO) {
            try {
                qualityStr = str.split("\\(")[1].substring(0, str.split("\\(")[1].length() - 1);
            } catch (Exception e) {
                qualityStr = str;
            }
            //System.out.println(qualityStr);
            if (qualityStr.equalsIgnoreCase("full-quality")) {
                this.quality = QualityX.FULL_QUALITY;
            } else if (qualityStr.equalsIgnoreCase("dvd-quality")) {
                this.quality = QualityX.DVD_QUALITY;
            } else if (qualityStr.equalsIgnoreCase("bluray-quality") || qualityStr.equalsIgnoreCase("bluray")) {
                this.quality = QualityX.BLURAY;
            } else if (qualityStr.equalsIgnoreCase("low-quality")) {
                this.quality = QualityX.LOW_QUALITY;
            } else if (qualityStr.equalsIgnoreCase("high-quality")) {
                this.quality = QualityX.HIGH_QUALITY;
            } else if (qualityStr.equalsIgnoreCase("bad-quality")) {
                this.quality = QualityX.BAD_QUALITY;
            } else if (qualityStr.equalsIgnoreCase("good-quality")) {
                this.quality = QualityX.GOOD_QUALITY;
            }

        }


    }

    public FTPMediaFile getParentFolder() {
        return this.parent;
    }

    public String getName() {
        return this.file.getName();
    }

    public QualityX getQuality() {
        return this.quality;
    }

    private void setMediaType() {
        File vf;//reffer to videoFormats.txt file
        File af;// reffer to audiFormats.txt file
        File df; //reffer to documentFormats.txt files
        File imagef; // reffer to imageFormats.txt files
        Scanner sc;



        try {

            fileFormat = this.file.getName().split("\\.")[this.file.getName().split("\\.").length - 1];

            if (this.file.isDirectory()) {
                fileFormat = "folder";
            }


        } catch (Exception e) {
            fileFormat = "Unknown";

        }
//        System.out.println("====== ." + fileFormat);
        settingFilesFolder = System.getProperty("user.dir") + "/files/"; //if this not works try this one + "\\files\\";
        vf = new File(settingFilesFolder + "videoFormats.txt");
        af = new File(settingFilesFolder + "audioFormats.txt");;
        df = new File(settingFilesFolder + "documentFormats.txt");;
        imagef = new File(settingFilesFolder + "imageFormats.txt");;

        try {

            if (!this.file.isDirectory()) {

                sc = new Scanner(vf);


                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
//                    System.out.println("-"+ line);
                    if (line.equals("." + fileFormat)) {
//                         System.out.println(" this file format " + this.fileFormat);
                        this.type = TypeX.VIDEO;

                    }
                }
                if (type == TypeX.UNKNOWN) {
                    sc = new Scanner(af);
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        if (line.equals("." + fileFormat)) {
                            this.type = TypeX.AUDIO;
                        }
                    }
                }
                if (type == TypeX.UNKNOWN) {
                    sc = new Scanner(df);
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        if (line.equals("." + fileFormat)) {
                            this.type = TypeX.DOCUMENT;
                        }
                    }
                }
                if (type == TypeX.UNKNOWN) {
                    sc = new Scanner(imagef);
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        if (line.equals("." + fileFormat)) {
                            this.type = TypeX.IMAGE;
                        }
                    }
                }
            }
            if (this.file.isDirectory()) {

                if (this.file.getName().equalsIgnoreCase(".webaxs")
                        || this.file.getName().equalsIgnoreCase("subtitles")
                        || this.file.getName().equalsIgnoreCase("trashbox")
                        || this.file.getName().equalsIgnoreCase("Temporary Items")
                        || this.file.getName().equalsIgnoreCase("zipes")
                        || this.file.getName().equalsIgnoreCase("Network Trash Folder")
                        || this.file.getName().equalsIgnoreCase("Temporary Items")) {
                    this.type = TypeX.OPTIONS_DIR;
                } else if (this.file.getName().equalsIgnoreCase("Books&AudioBooks")
                        || this.file.getName().equalsIgnoreCase("TvShows")
                        || this.file.getName().equalsIgnoreCase("Documentaries")
                        || this.file.getName().equalsIgnoreCase("Pictures&Videos")
                        || this.file.getName().equalsIgnoreCase("xxx-material")
                        || this.file.getName().equalsIgnoreCase("Audio&Music")
                        || this.file.getName().equalsIgnoreCase("Other")
                        || this.file.getName().equalsIgnoreCase("Tutorials")
                        || this.file.getName().equalsIgnoreCase("Movies")
                        || this.file.getName().equalsIgnoreCase("Music")
                        || this.file.getName().equalsIgnoreCase("Movies-Persian")) {
                    this.type = TypeX.CATEGORY_DIR;
                   
                } else {
                        this.type = TypeX.MEDIA_DIR;

                }
                if(parent.getName().equalsIgnoreCase("TvShows") )
                    this.type = TypeX.TVS_DIR;
                if(parent.getMediaType() != null && parent.getMediaType() == TypeX.TVS_DIR)
                    this.type = TypeX.SEASON_DIR;

            }
            
        } catch (Exception e) {
            // System.out.println(e);
        }

    }

    

    public boolean isDirContainsType(TypeX contentType) {
        if (this.isDirectory()) {
            MediaFile[] files = this.listMediaFiles();
            for (MediaFile f : files) {
                if (f.isMediaType(contentType)) {
                    return true;
                }
            }

        }

        return false;
    }

    public boolean isDirectory() {
        return this.getFTPFile().isDirectory();
    }

    public boolean isDirEmpty() {

        return this.listFTPMediaFiles().length <= 0;
    }

    public FTPFile getFTPFile() {
        return this.file;
    }

    public String getFormat() {

        return this.fileFormat;
    }

    public TypeX getMediaType() {

        return this.type;
    }

    public boolean isMediaType(TypeX t) {
        return this.type == t;
    }

    public String getFilePath() {
        return this.FILEPATH;
    }

    public String getAbsoluteFilePath() {
        return fc.getAbsoluteLoginPath() + "/" + this.FILEPATH;
    }
}
