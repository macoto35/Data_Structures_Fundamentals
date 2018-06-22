package array;

public class TreeNode<E> {
    private E key;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(E key) {
        this.key = key;
    }

    public E getKey() { return this.key; }

    public TreeNode getLeft() { return this.left; }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() { return this.right; }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
