package ar.com.pablocaamano.zipper;

import ar.com.pablocaamano.zipper.service.ZipService;
import ar.com.pablocaamano.zipper.service.ZipServiceImpl;

public class Zipper {

    /**
     * Compress all files in folder, zip files are saved in custom folder
     * @param inputPath directory with files to compress
     * @param outPath directory to write zip files
     */
    public static Boolean zipFiles(String inputPath, String outPath){
        ZipService service = new ZipServiceImpl();
        return service.zipDirectory(inputPath, outPath);
    }

    /**
     * Compress all files in folder
     * @param path directory with files to compress and location to write zip files
     */
    public static Boolean zipFiles(String path){
        ZipService service = new ZipServiceImpl();
        return service.zipDirectory(path);
    }
}
