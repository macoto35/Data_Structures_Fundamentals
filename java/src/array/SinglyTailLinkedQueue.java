package array;

public class SinglyTailLinkedQueue<E> {

    private Node head;

    private Node tail;

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
        for(int i = 0 ; i < size ; i++) {
            sb.append(tmp.key);
            sb.append(",");
            tmp = tmp.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    public void enQueue(E key) {
        Node node = new Node(key);

        if (head == null)
            head = node;

        if (tail != null)
            tail.next = node;

        tail = node;
        size++;
    }

    public E deQueue() {
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
