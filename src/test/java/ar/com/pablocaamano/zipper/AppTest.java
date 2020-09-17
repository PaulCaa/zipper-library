package ar.com.pablocaamano.zipper;

import static org.junit.Assert.assertTrue;

import ar.com.pablocaamano.zipper.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }



    @Test
    public void testCompress() throws IOException {
            String testPath = "./tmp/";
        try {
            if (FileUtils.makeDirectory(testPath)) {
                File f1 = new File(testPath + "test.txt");
                f1.createNewFile();
                Zipper zipper = Zipper.setup().input(testPath).build();
                zipper.compress();
            }
            boolean result = FileUtils.exists(testPath + "/zipper/zipper.zip");
            Assert.assertTrue(result);
        }catch(Exception exception) {
        }finally {
            if(FileUtils.exists(testPath)){
                FileUtils.cleanAndRemove(testPath);
            }
        }
    }
}
