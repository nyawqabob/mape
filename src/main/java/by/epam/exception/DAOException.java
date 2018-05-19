package by.epam.exception;

public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable ex) {
        super(message, ex);
    }
}
