public class ListNode {
    Patient data;
    ListNode next;
    ListNode(Patient o)
    {
        this(o, null);
    }

    ListNode(Patient o, ListNode nextNode)
    {
        data = o;
        next = nextNode;
    }

    public Patient getData() {
        return data;
    }

    public ListNode getNext() {
        return next;
    }
}
