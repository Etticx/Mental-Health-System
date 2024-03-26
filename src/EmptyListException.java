public class EmptyListException extends RuntimeException{
    public EmptyListException()
    {
        super("this list is empty");
    }
}