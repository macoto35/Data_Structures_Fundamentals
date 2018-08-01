package priorityQueue;

public class DisJointSetLinkedList {

    private Node[] tail = new Node[9];

    private int size = -1;

    private int maxSize = 9;

    private class Node {
        private int value;

        private Node next;

        private Node prev;

        public Node(int value) {
            this.value = value;
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= this.size; i++) {
            sb.append(this.tail[i].value);
            sb.append(",");
        }

        return sb.toString();
    }

    public void makeSet(int p) {
        if (this.size == this.maxSize - 1)
            throw new IndexOutOfBoundsException();

        Node node = new Node(p);
        this.tail[++this.size] = node;
    }

    public int find(int i) {
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();
        return this.tail[i].value;
    }

    public void union(int i, int j) {
        this.tail[i].next = this.tail[j];
        this.tail[j].prev = this.tail[i];

        Node node = this.tail[j].prev;
        while(node != null) {
            this.tail[node.value] = this.tail[j];
            node = node.prev;
        }
    }
}
