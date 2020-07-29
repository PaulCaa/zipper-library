package ar.com.pablocaamano.zipper;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

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
    public void testErrorCompression(){
        boolean result = Zipper.zipFiles("./zaraza");
        Assert.assertFalse(result);
    }
}
