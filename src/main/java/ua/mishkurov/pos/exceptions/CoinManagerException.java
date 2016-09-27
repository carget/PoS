package ua.mishkurov.pos.exceptions;

/**
 * Created by Anton_Mishkurov on 9/27/2016.
 */
public class CoinManagerException extends RuntimeException {

    public CoinManagerException(){
        super();
    }

    public CoinManagerException(String message){
        super(message);
    }

    public CoinManagerException(String message, Throwable cause){
        super(message, cause);
    }
}
