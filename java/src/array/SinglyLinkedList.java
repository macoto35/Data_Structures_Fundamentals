package array;

public class SinglyLinkedList<E> {

    private Node head;

    private Node tail;

    private int size = 0;

    public String toString() {
        if (head == null)
            return "[]";

        Node tmp = head;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0 ; i < size; i++) {
            sb.append(tmp.data);
            sb.append(",");
            tmp = tmp.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    private class Node {
        private E data;
        private Node next;

        private Node(E data) {
            this.data = data;
            this.next = null;
        }

        public String toString() {
            return String.valueOf(data);
        }
    }

    public void addFirst(E input) {
        Node node = new Node(input);

        node.next = head;
        head = node;
        size++;

        if (tail == null) {
            tail = node;
        }
    }

    public void addLast(E input) {
        Node node = new Node(input);

        node.next = null;
        size++;

        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    private Node node(int idx) {
        Node tmp = head;
        for(int i = 0 ; i < idx ; i++)
            tmp = tmp.next;

        return tmp;
    }

    // addBefore
    public void add(int idx, E input) {
        if (idx == 0) {
            addFirst(input);
        } else if (idx <= size) {
            Node newNode = new Node(input);
            Node node = node(idx - 1);

            newNode.next = node.next;
            node.next = newNode;
            size++;

            if(newNode.next == null) {
                tail = newNode;
            }
        }
    }

    public E removeFirst() {
        if (head == null) {
            return null;
        } else {
            Node node = head;

            head = node.next;
            if (head == null)
                tail = null;

            E returnValue = node.data;
            node = null;
            size--;

            return returnValue;
        }
    }

    public E remove(int idx) {
        if (idx == 0) {
            return removeFirst();
        } else if (idx < size) {
            Node prev = node(idx - 1);
            Node del = prev.next;

            prev.next = del.next;
            if (prev.next == null) {
                tail = prev;
            }
            E returnValue = del.data;
            del = null;
            size--;

            return returnValue;
        }

        return null;
    }

    public E removeLast() {
        if (size == 0)
            return null;

        return remove(size - 1);
    }

    public int size() {
        return this.size;
    }

    public E get(int idx) {
        Node node = this.node(idx);

        if (node == null)
            return null;

        return node.data;
    }

    public int indexOf(E data) {
        Node temp = head;

        for(int i = 0 ; i < size ; i++) {
            if (temp.data.equals(data)) {
                return i;
            }
            temp = temp.next;
        }

        return -1;
    }

    public ListIterator listIterator() {
        return new ListIterator();
    }

    class ListIterator {
        private Node lastReturned;
        private Node next;
        private int nextIdx;

        public ListIterator() {
            this.next = head;
        }

        public E next() {
            this.lastReturned = this.next;
            this.next = this.next.next;
            nextIdx++;

            return this.lastReturned.data;
        }

        public boolean hasNext() {
            return nextIdx < size();

        }

        public void add(E input) {
            Node newNode = new Node(input);

            if (lastReturned == null)
                head = newNode;
            else
                lastReturned.next = newNode;

            newNode.next = next;
            lastReturned = newNode;

            nextIdx++;
            size++;
        }

        public void remove() {
            if (nextIdx == 0)
                throw new IllegalStateException();

            SinglyLinkedList.this.remove(nextIdx- 1);
            nextIdx--;
        }
    }
}
