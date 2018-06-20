package array;

public class SinglyNoTailLinkedListStack<E> {

    private Node head;

    private int size;

    private class Node {
        private E key;
        private Node next;

        public Node(E key) {
            this.key = key;
        }
    }

    public String toString() {
        Node tmp = head;

        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0 ; i < size; i++) {
            sb.append(tmp.key);
            sb.append(",");
            tmp = tmp.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    public void push(E input) {
        Node node = new Node(input);

        node.next = head;
        head = node;
        size++;
    }

    public E top() {
        if (head == null)
            return null;
        return head.key;
    }

    public E pop() {
        if (head == null)
            return null;

        Node del = head;

        head = del.next;
        E returnValue = del.key;
        del = null;
        size--;

        return returnValue;
    }

    public boolean empty() {
        return head == null;
    }
}
