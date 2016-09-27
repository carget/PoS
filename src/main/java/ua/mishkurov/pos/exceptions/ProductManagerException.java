package ua.mishkurov.pos.exceptions;

/**
 * Created by Anton_Mishkurov on 9/27/2016.
 */
public class ProductManagerException extends RuntimeException {

    public ProductManagerException(){
        super();
    }

    public ProductManagerException(String message){
        super(message);
    }

    public ProductManagerException(String message, Throwable cause){
        super(message, cause);
    }
}
