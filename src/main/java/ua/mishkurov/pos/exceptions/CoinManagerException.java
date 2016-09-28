package ua.mishkurov.pos.exceptions;

/**
 *
 * @author Anton_Mishkurov
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
