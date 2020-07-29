package ar.com.pablocaamano.zipper.service;

public interface ZipService {
    Boolean zipDirectory(String directory);
    Boolean zipDirectory(String inputDir, String outDir);
}
