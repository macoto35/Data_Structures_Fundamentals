package binarySearchTree;

import java.util.Queue;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.Map;

public class ColourFlip {
    Node t1;
    Node t2;

    public String bfsPrint(Node node, String type) {
        Queue<Node> q = new LinkedList<Node>();
        StringJoiner sj = new StringJoiner(",");
        q.add(node);

        while(!q.isEmpty()) {
            Node tmp = q.poll();
            sj.add(tmp.getDataByType(type));

            if (tmp.left != null)
                q.add(tmp.left);

            if (tmp.right != null)
                q.add(tmp.right);
        }

        return sj.toString();
    }

    public void newArray(int n) {
        for (int i = 1 ; i < n + 1; i++) {
            this.t1 = this.avlInsert(this.t1, i, 'w');
            this.t2 = this.avlInsert(this.t2, i, 'b');
        }
    }

    public Node avlInsert(Node root, int key, char colour) {
        root = this.insert(root, key, colour);
        Node node = this.find(root, key);
        return this.rebalance(node);
    }

    private Node insert(Node root, int key, char colour) {
        if (root == null)
            return new Node(key, null, colour);

        Node node = this.find(root, key);
        Node newNode = new Node(key, node, colour);

        if (key < node.key)
            node.left = newNode;
        else
            node.right = newNode;

        return root;
    }

    private Node find(Node node, int key) {
        if (key == node.key)
            return node;

        if (key < node.key) {
            if (node.left == null)
                return node;
            return this.find(node.left, key);
        } else {
            if (node.right == null)
                return node;
            return this.find(node.right, key);
        }
    }

    private Node rebalance(Node node) {
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

    private int getHeight(Node node) {
        return node == null? 0 : node.height;
    }

    private int getSize(Node node) {
        return node == null ? 0 : node.size;
    }

    private void adjustHeightAndSize(Node node) {
        node.height = Math.max(this.getHeight(node.left), this.getHeight(node.right)) + 1;
        node.size = this.getSize(node.left) + this.getSize(node.right) + 1;
    }

    private void rebalanceRight(Node node) {
        Node l = node.left;
        if (this.getHeight(l.right) > this.getHeight(l.left))
            this.rotateLeft(l);
        this.rotateRight(node);
    }

    private void rebalanceLeft(Node node) {
        Node r = node.right;
        if (this.getHeight(r.left) > this.getHeight(r.right))
            this.rotateRight(r);
        this.rotateLeft(node);
    }

    private void rotateRight(Node node) {
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

    private void rotateLeft(Node node) {
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

    public Node merge(Node r1, Node r2) {
        Node root = this.findLargest(r1);
        r1 = this.avlDelete(root);
        //return mergeWithRoot(r1, r2, root);
        return avlMergeWithRoot(r1, r2, root);
    }

    private Node findLargest(Node node) {
        if (node.right != null)
            return this.findLargest(node.right);
        return node;
    }

    private Node avlDelete(Node node) {
        return this.rebalance(this.delete(node));
    }

    private Node delete(Node node) {
        if (node.right == null) {
            Node p = node.parent;
            Node l = node.left;

            if (l != null) {
                l.parent = p;
            }

            if (p != null) {
                if (p.left != null && p.left.key == node.key)
                    p.left = l;
                if (p.right != null && p.right.key == node.key)
                    p.right = l;

                return p;
            } else {
                return l;
            }
        } else {
            Node l = this.next(node);
            Node p = l.parent;
            Node r = l.right;

            node.key = l.key;
            p.left = r;
            if (r != null)
                r.parent = p;

            return p;
        }
    }

    private Node next(Node node) {
        if (node.right != null)
            return this.leftDescendant(node.right);
        else
            return this.rightAncestor(node.key, node.parent);
    }

    private Node leftDescendant(Node node) {
        if (node.left != null)
            return this.leftDescendant(node.left);
        else
            return node;
    }

    private Node rightAncestor(int key, Node node) {
        if (key == node.key)
            return node;

        if (node.parent != null)
            return this.rightAncestor(key, node.parent);
        else
            return null;
    }

    private Node mergeWithRoot(Node r1, Node r2, Node root) {
        root.left = r1;
        root.right = r2;

        if (r1 != null)
            r1.parent = root;
        if (r2 != null)
            r2.parent = root;

        return root;
    }

    private Node avlMergeWithRoot(Node r1, Node r2, Node root) {
        if (Math.abs(this.getHeight(r1) - this.getHeight(r2)) <= 1) {
            root = this.mergeWithRoot(r1, r2, root);
            this.adjustHeightAndSize(root);

            return root;
        } else if (this.getHeight(r1) > this.getHeight(r2)) {
            Node tmp = this.avlMergeWithRoot(r1.right, r2, root);
            r1.right = tmp;
            tmp.parent = r1;
            r1.parent = null;

            return this.rebalance(r1);
        } else if (this.getHeight(r2) > this.getHeight(r1)) {
            Node tmp = this.avlMergeWithRoot(r1, r2.left, root);
            r2.left = tmp;
            tmp.parent = r2;
            r2.parent = null;

            return this.rebalance(r2);
        }
        return null;
    }

    public Map<String, Node> split(Node node, int key) {
        return null;
    }

    public void flip(int idx) {

    }
}
