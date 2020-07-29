package ar.com.pablocaamano.zipper.service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Caamaño, Pablo
 * @since 29/07/2020
 * @link pablocaamano.com.ar
 */
public class ZipServiceImpl implements ZipService{

    private String inputDir = "";
    private String outputDir = "";
    private HashMap<String,String> zipped;
    private List<String> filesSucess;
    private List<String> errors;

    public ZipServiceImpl(){
        this.zipped = new HashMap<String, String>();
        this.filesSucess = new ArrayList<>();
        this.errors = new ArrayList<>();
    }

    @Override
    public Boolean zipDirectory(String path) {
        this.inputDir = path;
        this.outputDir = path;
        return zipFiles();
    }

    @Override
    public Boolean zipDirectory(String inputDir, String outDir) {
        this.inputDir = inputDir;
        this.outputDir = outDir;
        return zipFiles();
    }

    /**
     * Obtain all files in directory and validate zip output file
     * @return Boolean result state
     */
    private Boolean zipFiles(){
        File dir = new File(inputDir);
        if(dir.exists()){
            for(File f : dir.listFiles()){
                this.zipFile(f);
            }
            if(!filesSucess.isEmpty())
                return true;
            else
                return false;
        }else{
            return false;
        }
    }

    /**
     * Method to process file compression
     * @param f File to compress
     */
    private void zipFile(File f) {
        try {
            ZipOutputStream buffer = new ZipOutputStream(new FileOutputStream((inputDir + f.getName().concat(".zip"))));
            ZipEntry input = new ZipEntry(f.getName());
            buffer.putNextEntry(input);
            FileInputStream inputStream = new FileInputStream(inputDir + input.getName());
            int r = 0;
            byte[] b = new byte[1024];
            while(0<(r=inputStream.read(b))){
                buffer.write(b,0,r);
            }
            filesSucess.add(f.getName().concat(".zip"));
            inputStream.close();
            buffer.close();
        }catch(Exception exception){
            if(exception instanceof IOException){
                this.errors.add(f.getName());
            }else{

            }
        }
    }
}
