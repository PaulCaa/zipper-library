package ar.com.pablocaamano.zipper.service;

import ar.com.pablocaamano.zipper.exception.CompressionProcessException;
import ar.com.pablocaamano.zipper.exception.InvalidParameterException;
import ar.com.pablocaamano.zipper.util.FileUtils;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author Caamaño, Pablo
 * @since 16/09/2020
 * @link pablocaamano.com.ar
 */
public class ZipperService {

    private static final String OSX_HIDDEN_FILES = ".DS_STORE";
    private static final String ZIP_EXTENSION = ".zip";
    private static final String DEFAULT_OUTPUT = "zipper";

    private String inputPath;
    private String outputPath;
    private String fileName;
    private ZipOutputStream zipOutputStream;
    private static ZipperService instance = null;

    public ZipperService(String inputPath, String outputPath){
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public ZipperService(String inputPath, String outputPath, String fileName){
        paramValidation("inputPath",inputPath);
        this.inputPath = FileUtils.checkDirectoyrFormat(inputPath);
        this.outputPath = outputPath;
        this.fileName = fileName;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Unzip input file
     * @return
     * @throws IOException
     */
    public void unzip() {
        try {
            File zip = new File(inputPath);
            FileInputStream fileInput = new FileInputStream(zip);
            ZipInputStream zipInput = new ZipInputStream(fileInput);
            ZipEntry entry;
            while ((entry = zipInput.getNextEntry()) != null) {
                String entryFilePath = this.outputPath + entry.getName();
                if (entry.getName().endsWith(OSX_HIDDEN_FILES)) {
                    continue;
                }
                if (entry.isDirectory()) {
                    (new File(entryFilePath)).mkdirs();
                } else {
                    FileOutputStream fileOut = new FileOutputStream(entryFilePath);
                    BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
                    int i = zipInput.read();
                    while (i != -1) {
                        bufferOut.write(i);
                        i = zipInput.read();
                    }
                    bufferOut.flush();
                    bufferOut.close();
                    fileOut.flush();
                    fileOut.close();
                }
            }
            zipInput.close();
            fileInput.close();
        }catch(Exception exception){
            throw new CompressionProcessException("Unzip process fail", exception);
        }
    }

    public void zip(){
        zipProcess("");
    }

    public void zip(String fileName){
        zipProcess(fileName);
    }

    private void zipProcess(String fileName){
        try {
            File input = new File(inputPath + fileName);
            if (this.fileName == null) {
                this.fileName = input.getName();
                this.fileName.replace(".","");
                this.fileName.replace("/", "");
            }
            if(outputPath == null || outputPath.length() == 0){
                outputPath = FileUtils.checkDirectoyrFormat(inputPath + DEFAULT_OUTPUT);
                FileUtils.makeDirectory(outputPath);
            }
            FileOutputStream outputStream = new FileOutputStream(outputPath + this.fileName + ZIP_EXTENSION);
            this.zipOutputStream = new ZipOutputStream(outputStream);
            addFile("", input);
            zipOutputStream.flush();
            zipOutputStream.close();
            outputStream.flush();
            outputStream.close();
        }catch (Exception exception){
            throw new CompressionProcessException("Compression process fail", exception);
        }
    }

    private void addFile(String path, File file) throws IOException{
        if(file.isDirectory()){
            for(File f : file.listFiles()){
                if(f.getName().endsWith(DEFAULT_OUTPUT)){
                    continue;
                }
                String filePath = path + file.getName() + "/";
                ZipEntry entry = new ZipEntry(filePath);
                zipOutputStream.putNextEntry(entry);
                zipOutputStream.flush();
                addFile(filePath,f);
            }
        }else{
            byte buffer[] = new byte[1024];
            FileInputStream fileInput = new FileInputStream(file);
            BufferedInputStream bufferInput = new BufferedInputStream(fileInput);
            zipOutputStream.putNextEntry(new ZipEntry(path + file.getName()));
            int i;
            while((i = bufferInput.read()) > 0){
                zipOutputStream.write(buffer,0,i);
                zipOutputStream.flush();
            }
            fileInput.close();
            bufferInput.close();
        }
    }

    private void paramValidation(String paramName, String paramValue){
        if(paramValue == null || paramValue.length() == 0){
            throw new InvalidParameterException("Null or empty param '" + paramName + "'");
        }
    }
}
