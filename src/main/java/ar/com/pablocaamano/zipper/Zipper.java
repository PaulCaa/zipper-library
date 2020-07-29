package ar.com.pablocaamano.zipper;

import ar.com.pablocaamano.zipper.service.ZipService;
import ar.com.pablocaamano.zipper.service.ZipServiceImpl;

import java.io.Serializable;

public class Zipper implements Serializable {

    /**
     * Compress all files in folder, zip files is saved in custom folder
     * @param inputPath directory with files to compress
     * @param outPath directory to write zip files
     * @return Boolean operation state
     */
    public static Boolean zipFiles(String inputPath, String outPath){
        ZipService service = new ZipServiceImpl();
        return service.zipDirectory(inputPath, outPath);
    }

    /**
     * Compress all files in folder
     * @param path directory with files to compress and location to write zip files
     * @return Boolean operation state
     */
    public static Boolean zipFiles(String path){
        ZipService service = new ZipServiceImpl();
        return service.zipDirectory(path);
    }

    /**
     * Compress a single file
     * @param pathFile directory + filename
     * @return Boolean operation state
     */
    public static Boolean zipFile(String pathFile){
        ZipService service = new ZipServiceImpl();
        return service.zipFile(pathFile);
    }

    /**
     * Compress a single file, zip file is saved in custom folder
     * @param pathFile directory + filename
     * @param outDir directory to write zip files
     * @return Boolean operation state
     */
    public static Boolean zipFile(String pathFile, String outDir){
        ZipService service = new ZipServiceImpl();
        return service.zipFile(pathFile,outDir);
    }
}
