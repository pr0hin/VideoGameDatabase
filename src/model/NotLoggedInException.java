package model;

/**
 * Created by rohinpatel on 2016-03-27.
 */
public class NotLoggedInException extends Exception {
    public NotLoggedInException() {
        super();
    }
    public NotLoggedInException(String message) {
        super(message);
    }
}
