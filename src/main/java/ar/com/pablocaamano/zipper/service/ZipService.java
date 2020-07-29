package ar.com.pablocaamano.zipper.service;

public interface ZipService {
    Boolean zipDirectory(String directory);
    Boolean zipDirectory(String inputDir, String outDir);

    Boolean zipFile(String pathFile);
    Boolean zipFile(String pathFile, String outputDir);
}
