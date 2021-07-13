package exceptions;

public class FileNotFound extends RuntimeException{

    public FileNotFound(String message, Exception e)
    {
        super(message,e);
    }

    public FileNotFound(String message)
    {
        super(message);
    }
}
