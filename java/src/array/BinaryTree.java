package array;

public class BinaryTree {

    private TreeNode root;

    private StringBuffer sb;

    public void setRoot(TreeNode node) {
        this.root = node;
    }

    public String depthFirstPreOrderTraverse() {
        sb = new StringBuffer();
        dfsPreOrder(this.root);

        return sb.toString();
    }

    private void dfsPreOrder(TreeNode node) {
        if (node == null)
            return;

        sb.append(node.getKey());
        sb.append(", ");

        dfsPreOrder(node.getLeft());
        dfsPreOrder(node.getRight());
    }

    public String depthFirstInOrderTraverse() {
        sb = new StringBuffer();
        dfsInOrder(this.root);

        return sb.toString();
    }

    private void dfsInOrder(TreeNode node) {
        if (node == null)
            return;

        dfsInOrder(node.getLeft());

        sb.append(node.getKey());
        sb.append(", ");

        dfsInOrder(node.getRight());
    }

    public String depthFirstPostOrderTraverse() {
        sb = new StringBuffer();
        dfsPostOrder(this.root);

        return sb.toString();
    }

    private void dfsPostOrder(TreeNode node) {
        if (node == null)
            return;

        dfsPostOrder(node.getLeft());
        dfsPostOrder(node.getRight());

        sb.append(node.getKey());
        sb.append(", ");
    }

    private class Queue<E> {
        private Node head;

        private Node tail;

        private class Node {
            public E key;

            public Node next;

            public Node(E key) {
                this.key = key;
            }
        }

        public void enqueue(E key) {
            Node node = new Node(key);

            if (head == null)
                head = node;

            if (tail != null)
                tail.next = node;

            tail = node;
        }

        public E dequeue() {
            if (head == null)
                return null;

            Node del = head;
            E returnValue = del.key;
            head = del.next;
            del = null;

            return returnValue;
        }

        public boolean empty() {
            return head == null;
        }
    }

    public String breathFirst() {
        sb = new StringBuffer();

        Queue<TreeNode> queue = new Queue<TreeNode>();
        queue.enqueue(this.root);

        while(!queue.empty()) {
            TreeNode<String> node = queue.dequeue();
            sb.append(node.getKey());
            sb.append(", ");

            if (node.getLeft() != null)
                queue.enqueue(node.getLeft());

            if (node.getRight() != null)
                queue.enqueue(node.getRight());
        }

        return sb.toString();
    }
}
