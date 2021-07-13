package exceptions;

public class FrameDivisionException extends RuntimeException{

    public FrameDivisionException(String message, Exception exception)
    {
        super(message , exception);
    }

    public FrameDivisionException(String message)
    {
        super(message);
    }
}
