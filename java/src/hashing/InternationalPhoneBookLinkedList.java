package hashing;

public class InternationalPhoneBookLinkedList {
    private Node head;

    private Node tail;

    private class Node {
        private String number;

        private String name;

        private Node next;

        private Node prev;

        public Node(String number, String name) {
            this.number = number;
            this.name = name;
        }
    }

    public void setName(String number, String name) {
        Node node = new Node(number, name);

        if (this.head == null)
            this.head =  node;

        if (this.tail != null) {
            this.tail.next = node;
            node.prev = this.tail;
        }

        this.tail = node;
    }

    public String getName(String number) {
        Node node = this.head;

        while (node != null) {
            if (node.number == number)
                return node.name;
            node = node.next;
        }

        return null;
    }
}
