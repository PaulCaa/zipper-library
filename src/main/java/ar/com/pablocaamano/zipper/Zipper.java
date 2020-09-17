package ar.com.pablocaamano.zipper;

import ar.com.pablocaamano.zipper.service.ZipperService;
import ar.com.pablocaamano.zipper.util.FileUtils;

import java.io.Serializable;

/**
 * @author Caamaño, Pablo
 * @since 29/07/2020
 * @update 16/09/2020
 * @link pablocaamano.com.ar
 */
public class Zipper implements Serializable {

    private static Zipper instance = null;

    private ZipperService service;
    private String input;
    private String outputPath;
    private String fileName;

    private Zipper(){}

    /**
     * First step, initialize zipper
     * @return
     */
    public static Zipper setup(){
        if(instance != null){
            return new Zipper();
        }
        return instance;
    }

    /**
     * Define input path or path + file name
     * @param path to read input
     * @return
     */
    public Zipper input(String path){
        this.input = path;
        return this;
    }

    /**
     * Define output location
     * @param path to write output
     * @return
     */
    public Zipper output(String path){
        this.outputPath = path;
        return this;
    }

    /**
     * Define custom name to zip file
     * @param zipName name of zip file
     * @return
     */
    public Zipper setCustom(String zipName){
        this.fileName = zipName;
        return this;
    }

    /**
     * Finish Zipper setup
     * @return
     */
    public Zipper build(){
        this.service = new ZipperService(input,outputPath,fileName);
        return this;
    }

    /**
     * Compress all input directory
     */
    public void compress(){
        service.zip();
    }

    /**
     * compress specific file of input directory
     * @param fileName
     */
    public void compressFile(String fileName){
        service.zip(fileName);
    }

    public void decompressFile(String fileName){
        if(service.getOutputPath() == null){
            String outDir = service.getInputPath() + "/zipper/";
            FileUtils.makeDirectory(outDir);
            service.setOutputPath(outDir);
        }
        service.unzip();
    }
}
