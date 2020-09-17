package ar.com.pablocaamano.zipper.exception;

/**
 * @author Caamaño, Pablo
 * @since 16/09/2020
 * @link pablocaamano.com.ar
 */
public class InvalidParameterException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Any parameter is invalid (null or empty)";
    public InvalidParameterException(){
        super(DEFAULT_MESSAGE);
    }

    public InvalidParameterException(String message){
        super(message);
    }
}
