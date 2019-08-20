package binarySearchTree;

import java.util.Queue;
import java.util.LinkedList;
import java.util.StringJoiner;

public class OrderStatistics {
    public Node find(int key, Node node) {
        if (key == node.key)
            return node;

        if (key < node.key) {
            if (node.left != null)
                return this.find(key, node.left);
            return node;
        } else {
            if (node.right != null)
                return this.find(key, node.right);
            return node;
        }
    }

    public Node insert(int key, Node root) {
        if (root == null)
            return new Node(key, null);

        Node node = this.find(key, root);
        Node newNode = new Node(key, node);

        if (key < node.key)
            node.left = newNode;
        else
            node.right = newNode;

        return root;
    }

    public Node avlInsert(int key, Node root) {
        root = this.insert(key, root);
        Node node = this.find(key, root);
        return this.rebalance(node);
    }

    public Node rebalance(Node node) {
        if (this.getHeight(node.left) > this.getHeight(node.right) + 1)
            this.rebalanceRight(node);

        if (this.getHeight(node.right) > this.getHeight(node.left) + 1)
            this.rebalanceLeft(node);

        this.adjustHeightAndSize(node);

        Node p = node.parent;
        if (p != null)
            return this.rebalance(p);
        else
            return node;
    }

    public int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    public int getSize(Node node) {
        return node == null ? 0 : node.size;
    }

    public void rebalanceRight(Node node) {
        Node l = node.left;
        if (this.getHeight(l.right) > this.getHeight(l.left))
            this.rotateLeft(l);
        rotateRight(node);
    }

    public void rebalanceLeft(Node node) {
        Node r = node.right;
        if (this.getHeight(r.left) > this.getHeight(r.right))
            this.rotateRight(r);
        this.rotateLeft(node);
    }

    public void rotateRight(Node node) {
        Node p = node.parent;
        Node l = node.left;
        Node r = l.right;

        if (p != null) {
            if (p.left != null && p.left.key == node.key)
                p.left = l;
            if (p.right != null && p.right.key == node.key)
                p.right = l;
        }
        l.parent = p;

        l.right = node;
        node.parent = l;

        node.left = r;
        if (r != null)
            r.parent = node;

        this.adjustHeightAndSize(node);
        this.adjustHeightAndSize(l);
    }

    public void rotateLeft(Node node) {
        Node p = node.parent;
        Node r = node.right;
        Node l = r.left;

        if (p != null) {
            if (p.left != null && p.left.key == node.key)
                p.left = r;
            if (p.right != null && p.right.key == node.key)
                p.right = r;
        }
        r.parent = p;

        r.left = node;
        node.parent = r;

        node.right = l;
        if (l != null)
            l.parent = node;

        this.adjustHeightAndSize(node);
        this.adjustHeightAndSize(r);
    }

    public void adjustHeightAndSize(Node node) {
        node.height = Math.max(this.getHeight(node.left), this.getHeight(node.right)) + 1;
        node.size = this.getSize(node.left) + this.getSize(node.right) + 1;
    }

    public Node orderStatistic(Node root, int n) {
        int s = this.getSize(root.left);

        if (n == s + 1)
            return root;
        else if (n < s + 1)
            return this.orderStatistic(root.left, n);
        else
            return this.orderStatistic(root.right, n - s - 1);
    }

    public int reverseOrderStatistic(Node root, int key) {
        if (key < root.key) {
            return this.reverseOrderStatistic(root.left, key);
        } else {
            int n = this.getSize(root.left) + 1;

            if (key > root.key)
                return n + this.reverseOrderStatistic(root.right, key);
            else
                return n;
        }
    }

    public String bfsPrint(Node root, String type) {
        StringJoiner sj = new StringJoiner(",");
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        while(!q.isEmpty()) {
            Node node = q.poll();
            sj.add(node.getDataByType(type));

            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
        }

        return sj.toString();
    }
}
