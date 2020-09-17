package ar.com.pablocaamano.zipper.util;

import java.io.File;
import java.util.HashMap;

/**
 * @author Caamaño, Pablo
 * @since 16/09/2020
 * @link pablocaamano.com.ar
 */
public class FileUtils {

    /**
     * String path format validation
     * @param path String location of directory
     * @return String path of directory end with "/"
     */
    public static String checkDirectoyrFormat(String path) {
        if (!path.endsWith("/")) {
            return new StringBuilder().append(path).append("/").toString();
        }
        return path;
    }

    /**
     * Validate if a file or directory exists
     * @param path directory path or path + file name
     * @return boolean
     */
    public static boolean exists(String path){
        return new File(path).exists();
    }

    /**
     * Delete all files and sub directories
     * @param path String location of directory to clean
     * @return HashMap<String,String> files and path of deleted files
     */
    public HashMap<String,String> cleanDirectory(String path){
        File dirToClean = new File(checkDirectoyrFormat(path));
        HashMap<String,String> deleted = new HashMap<>();
        if(dirToClean.list().length == 0) {
            return deleted;
        }
        for(File file : dirToClean.listFiles()) {
            boolean result;
            if(file.isDirectory()) {
                cleanDirectory(file.getAbsolutePath());
                result = file.delete();
            }else{
                result = file.delete();
            }
            if(result) {
               deleted.put(file.getName(),file.getAbsolutePath());
            }
        }
        return deleted;
    }

    /**
     * Create new directory
     * @param path location + new directory to create
     * @return boolean
     */
    public static boolean makeDirectory(String path){
        return new File(path).mkdir();
    }

    /**
     * Remove  dir or file
     * @param path directory full path or path + file name
     * @return boolean
     */
    public static boolean remove(String path){
        File file = new File(path);
        if(!file.exists()){
            return false;
        }
        return file.delete();
    }

    /**
     * Clean files in directory and remove this
     * @param path directory path
     * @return boolean
     */
    public static boolean cleanAndRemove(String path){
        File file = new File(path);
        if(!file.exists()){
            return false;
        }
        if(file.isDirectory()){
            for(File f : file.listFiles()){
                if(f.isDirectory()){
                    cleanAndRemove(f.getPath());
                }
                remove(f.getPath());
            }
        }
        return file.delete();
    }
}
