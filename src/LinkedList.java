public class LinkedList {
    public ListNode first,current,last;

    public LinkedList()
    {
        first = last = current = null;
    }

    public boolean isEmpty() {return first == null;}

    public void insertAtFront(Patient insertItem)
    {
        if(isEmpty())
            first = last = new ListNode(insertItem);
        else
            first = new ListNode(insertItem,first);
    }

    public void insertAtBack(Patient insertItem)
    {
        if(isEmpty())
            first = last = new ListNode(insertItem);
        else
            last = last.next = new ListNode(insertItem);
    }

    //remove node anywhere in the list
    public Patient removeNode(Patient fObj) {
        Patient removeItem = null;
        ListNode previous = null;

        if (isEmpty()) {
            throw new EmptyListException();
        }

        current = first;

        // Check if the first node is the one to be removed
        if (first.data.equals(fObj)) {
            removeItem = first.data;
            first = first.next;
            return removeItem;
        }

        // Traverse the list to find the node to be removed
        while (current != null && !current.data.equals(fObj)) {
            previous = current;
            current = current.next;
        }

        // If the node is not found
        if (current == null) {
            return null;
        }

        // Remove the node
        removeItem = current.data;
        previous.next = current.next;
        current.next = null;

        return removeItem;
    }

    public Patient removeFromFront() throws EmptyListException
    {
        Patient removeItem = null;
        if(isEmpty())
            throw new EmptyListException();
        removeItem = first.data;
        if(first.equals(last))
            first = last = null;
        else
            first = first.next;

        return removeItem;
    }

    public Patient removeFromBack() throws EmptyListException
    {
        Patient removeItem = null;
        if(isEmpty())
            throw new EmptyListException();
        removeItem = last.data;
        if(first.equals(last))
            first = last = null;
        else
        {
            current = first;
        }

        while(current.next != last)
            current = current.next;
        last = current;
        current.next = null;
        return removeItem;

    }

    public Patient getHead()
    {
        if(isEmpty())
            return null;
        else
        {
            current =first;
            return current.data;
        }
    }

    public Patient getNext()
    {
        if(current != last)
        {
            current = current.next;
            return current.data;
        }
        else
        {
            return null;
        }
    }

    //This method calculate the size of the linked list
    public int getSize()
    {
        int i = 1;
        if(isEmpty())
        {
            return 0;
        }
        else
        {
            current = first;
            while(current != last)
            {
                i++;
                current = current.next;
            }
            return i;
        }

    }

    //this method return status whether the linked list is empty or has elements.
    public String hasElements()
    {
        if (first == null)
            return "This List has no elements";
        else
        {
            return "This list has elements";
        }
    }


    //this method will display details of all elements in the list
    public String getDetails()
    {
        String finalOutput = "";
        boolean finished = false;
        if(isEmpty())
            return null;
        else
        {
            current = first;
            while(!finished)
            {
                if(current == last)
                    finished = true;
                finalOutput += "\n" + current.data.toString();
                current = current.next;
            }   
            return finalOutput;
        }
    }
}
