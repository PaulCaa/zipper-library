package ar.com.pablocaamano.zipper.exception;

/**
 * @author Caamaño, Pablo
 * @since 16/09/2020
 * @link pablocaamano.com.ar
 */
public class CompressionProcessException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Error in zip file process";

    public CompressionProcessException(){
        super(DEFAULT_MESSAGE);
    }

    public CompressionProcessException(Throwable cause){
        super(DEFAULT_MESSAGE, cause);
    }

    public CompressionProcessException(String message){
        super(message);
    }

    public CompressionProcessException(String message, Throwable cause){
        super(message, cause);
    }
}
