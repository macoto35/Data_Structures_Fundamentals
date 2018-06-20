package array;

public class DoublyLinkedList<E> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private E data;
        private Node next;
        private Node prev;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public String toString() {
            return String.valueOf(this.data);
        }
    }

    public String toString() {
        Node tmp = head;

        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0 ; i < size ; i++) {
            sb.append(tmp.data);
            sb.append(",");
            tmp = tmp.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    public void addFirst(E input) {
        Node node = new Node(input);
        node.next = head;

        if (head != null)
            head.prev = node;

        head = node;

        if (tail == null)
            tail = node;

        size++;
    }

    public void addLast(E input) {
        if (head == null) {
            addFirst(input);
        } else {
            Node node = new Node(input);
            node.prev = tail;
            tail.next = node;
            tail = node;

            size++;
        }
    }

    private Node node(int idx) {
        if (idx < size / 2) {
            Node tmp = head;

            for(int i = 0; i < idx; i++)
                tmp = tmp.next;

            return tmp;
        } else {
            Node tmp = tail;

            for(int i = size - 1; i > idx; i--)
                tmp = tmp.prev;

            return tmp;
        }
    }

    public void add(int idx, E input) {
        if (idx == 0) {
            addFirst(input);
        } else if (size == idx) {
            addLast(input);
        } else {
            Node next = node(idx);
            Node prev = next.prev;
            Node newNode = new Node(input);

            prev.next = newNode;
            newNode.prev = prev;
            newNode.next = next;
            next.prev = newNode;

            size++;
        }
    }

    public E removeFirst() {
        if (head == null)
            return null;

        Node delete = head;
        head = delete.next;

        if (head != null) {
            head.prev = null;
        }

        E returnValue = delete.data;
        delete = null;
        size--;

        return returnValue;
    }

    public E remove(int idx) {
        if (idx == 0) {
            return removeFirst();
        } else if (idx < size) {
            Node delete = node(idx);

            Node prev = delete.prev;
            Node next = delete.next;
            prev.next = next;
            if (next != null)
                next.prev = prev;
            else
                tail = prev;

            E returnValue = delete.data;
            delete = null;
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
        if (head == null || idx >= size)
            return null;

        return node(idx).data;
    }

    public int indexOf(E data) {
        Node tmp = head;

        for (int i = 0 ; i < size ; i++) {
            if (tmp.data.equals(data))
                return i;
            tmp = tmp.next;
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

        ListIterator() {
            next = head;
        }

        public E next() {
            lastReturned = next;
            next = next.next;
            nextIdx++;

            return lastReturned.data;
        }

        public boolean hasNext() {
            return nextIdx < size();
        }

        public E previous() {
            lastReturned = next = (next == null) ? tail : next.prev;

            nextIdx--;
            return lastReturned.data;
        }

        public boolean hasPrevious() {
            return nextIdx > 0;
        }

        public void add (E input) {
            Node node = new Node(input);
            node.next = next;

            if (lastReturned == null) {
                head = node;
            } else {
                node.prev = lastReturned;
                lastReturned.next = node;
            }

            if (next == null)
                tail = node;
            else
                next.prev = node;

            lastReturned = node;
            next = node.next;
            size++;
            nextIdx++;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node prevDel = lastReturned.prev;
            Node del = lastReturned;
            Node nextDel = lastReturned.next;

            if (prevDel == null)
                head = nextDel;
            else
                prevDel.next = nextDel;

            if (nextDel == null)
                tail = prevDel;
            else
                nextDel.prev = prevDel;

            del = null;
            lastReturned = prevDel == null ? head : prevDel;
            next = lastReturned.next;

            size--;
            nextIdx--;
        }
    }
}
