package binarySearchTree;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class AvlTreeMs {
    public Integer[] printBfs(Node node) {
        List<Integer> result = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(node);

        while(!queue.isEmpty()) {
            Node tmp = queue.removeFirst();
            result.add(tmp.key);

            if (tmp.left != null)
                queue.addLast(tmp.left);
            if (tmp.right != null)
                queue.addLast(tmp.right);
        }

        return result.toArray(new Integer[result.size()]);
    }

    public Integer[] printBfsHeight(Node node) {
        List<Integer> result = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.addLast(node);

        while(!queue.isEmpty()) {
            Node tmp = queue.removeFirst();
            result.add(tmp.height);

            if (tmp.left != null)
                queue.addLast(tmp.left);

            if (tmp.right != null)
                queue.addLast(tmp.right);
        }

        return result.toArray(new Integer[result.size()]);
    }

    public void mergeWithRoot(Node r1, Node r2, Node root) {
        root.left = r1;
        root.right = r2;

        root.parent = null;

        r1.parent = root;
        r2.parent = root;
    }

    public Node merge(Node r1, Node r2) {
        Node root = this.largestNode(r1);
        this.remove(root);
        this.mergeWithRoot(r1, r2, root);

        return root;
    }

    private Node largestNode(Node node) {
        if (node.right != null)
            return this.largestNode(node.right);
        else
            return node;
    }

    private Node remove(Node node) {
        if (node.right == null) {
            Node p = node.parent;
            Node l = node.left;

            if (l != null)
                l.parent = p;

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
            Node next = this.next(node);
            Node p = next.parent;
            Node r = next.right;

            node.key = next.key;

            if (p != null) {
                if (p.left != null && p.left.key == next.key)
                    p.left = r;
                if (p.right != null && p.right.key == next.key)
                    p.right = r;
            }

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

        return node;
    }

    private Node rightAncestor(int key, Node node) {
        if (key < node.key)
            return node;

        if (node.parent != null)
            return this.rightAncestor(key, node.parent);
        else
            return null;
    }

    public Node avlMerge(Node r1, Node r2) {
        Node root = this.largestNode(r1);
        this.remove(root);
        return this.avlTreeMergeWithRoot(r1, r2, root);
    }

    private Node avlTreeMergeWithRoot(Node r1, Node r2, Node root) {
        if (Math.abs(r1.height - r2.height) <= 1) {
            this.mergeWithRoot(r1, r2, root);
            root.height = Math.max(r1.height, r2.height) + 1;

            return root;
        } else if (r1.height > r2.height) {
            Node r = this.avlTreeMergeWithRoot(r1.right, r2, root);
            r1.right = r;
            r.parent = r1;

            r1.parent = null;
            this.rebalance(r1);

            return this.getRoot(r1);
        } else if (r1.height < r2.height) {
            Node r = this.avlTreeMergeWithRoot(r1, r2.left, root);
            r2.left = r;
            r.parent = r2;

            r2.parent = null;
            this.rebalance(r2);

            return this.getRoot(r2);
        }

        return null;
    }

    private void rebalance(Node node) {
        if (this.getHeight(node.left) > this.getHeight(node.right) + 1)
            this.rebalanceRight(node);

        if (this.getHeight(node.right) > this.getHeight(node.left) + 1)
            this.rebalanceLeft(node);

        this.adjustHeight(node);

        if (node.parent != null)
            this.rebalance(node.parent);
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private void rebalanceRight(Node node) {
        Node l = node.left;
        if (this.getHeight(l.right) > this.getHeight(l.left))
            this.rotateLeft(l);
        this.rotateRight(node);
    }

    private void rebalanceLeft(Node node) {
        Node r = node.right;
        if(this.getHeight(r.left) > this.getHeight(r.right))
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
    }

    private int adjustHeight(Node node) {
        if (node == null)
            return 0;
        return node.height = Math.max(this.adjustHeight(node.left), this.adjustHeight(node.right)) + 1;
    }

    private Node getRoot(Node node) {
        if (node.parent != null)
            return this.getRoot(node.parent);
        return node;
    }

    public Map<String, Node> split(Node root, int key) {
        if (root == null)
            return null;

        if (key < root.key) {
            Map<String, Node> result = this.split(root.left, key);
            this.mergeWithRoot(result.get("r2"), root.right, root);
            result.put("r2", root);

            return result;
        } else if (key > root.key) {
            Map<String, Node> result = this.split(root.right, key);
            this.mergeWithRoot(root.left, result.get("r1"), root);
            result.put("r1", root);

            return result;
        } else {
            Map<String, Node> result = new HashMap<>();
            result.put("r1", root.left);
            result.put("r2", root.right);

            return result;
        }
    }

    public Map<String, Node> avlSplit(Node root, int key) {
        if (root == null)
            return null;

        if (key < root.key) {
            Map<String, Node> result = this.split(root.left, key);
            result.put("r2", this.avlTreeMergeWithRoot(result.get("r2"), root.right, root));

            return result;
        } else if (key > root.key) {
            Map<String, Node> result = this.split(root.right, key);
            result.put("r1", this.avlTreeMergeWithRoot(root.left, result.get("r1"), root));

            return result;
        } else {
            Map<String, Node> result = new HashMap<>();
            result.put("r1", root.left);
            result.put("r2", root.right);

            return result;
        }
    }
}
