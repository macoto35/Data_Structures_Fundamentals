package binarySearchTree;

public class Node {
    public int key;

    public Node parent;

    public Node left;

    public Node right;

    public int height = 1;

    public int size = 1;

    public char colour;

    public Node(int key, Node parent) {
        this.key = key;
        this.parent = parent;
    }

    public Node(int key, Node parent, int height) {
        this.key = key;
        this.parent = parent;
        this.height = height;
    }

    public Node(int key, Node parent, int height, int size) {
        this.key = key;
        this.parent = parent;
        this.height = height;
        this.size = size;
    }

    public Node(int key, Node parent, char colour) {
        this.key = key;
        this.parent = parent;
        this.colour = colour;
    }

    public String getDataByType(String type) {
        switch (type) {
            case "key":
                return Integer.toString(this.key);
            case "height":
                return Integer.toString(this.height);
            case "size":
                return Integer.toString(this.size);
            case "colour":
                return Character.toString(this.colour);
        }
        return null;
    }
}
