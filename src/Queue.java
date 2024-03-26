import java.util.LinkedList;
//the Queue class with a LinkedList field

public class Queue
{
    protected LinkedList list;

    //queue object has been initialized
    public Queue()
    {
        list = new LinkedList();
    } //default constructor

    //check whether queue object has no element or otherwise
    public boolean isEmpty()
    {
        return list.isEmpty();
    }//method isEmpty

    //determine the no of elements in the queue object
    public int size()
    {
        return list.size();
    }// method size

    //insert element at the back of the queue object
    public void enqueue(Object element)
    {
        list.addLast(element);
    } //method enqueue

    //remove the front element from the queue object
    public Object dequeue()
    {
        return list.removeFirst();
    } //method dequeue

    //the element at the front of the queue object has been returned
    public Object front()
    {
        return list.getFirst();
    } //method front

    //the element at the end of the queue object has been returned
    public Object rear()
    {
        return list.getLast();
    } //method rear


} // Queue class
