package binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AvlTreeMsTest {
    AvlTreeMs tree;

    @BeforeEach
    public void setUp() {
        tree = new AvlTreeMs();
    }

    private Node getR1Left() {
        Node root = new Node(3, null, 4);
        Node left = root.left = new Node(2, root, 2);
        Node right = root.right = new Node(5, root, 3);
        left.left = new Node(1, left, 1);
        right.left = new Node(4, right, 1);
        Node rr = right.right = new Node(7, right, 2);
        rr.left = new Node(6, rr, 1);

        return root;
    }

    private Node getR1WithRootLeft() {
        Node root = new Node(3, null, 5);
        Node left = root.left = new Node(2, root, 2);
        Node right = root.right = new Node(5, root, 4);
        left.left = new Node(1, left, 1);
        right.left = new Node(4, right, 1);
        Node rr = right.right = new Node(8, right, 3);
        rr.left = new Node(7, rr, 2);
        rr.left.left = new Node(6, rr, 1);

        return root;
    }

    private Node getR2Left() {
        Node root = new Node(9, null, 3);
        root.right = new Node(11, root, 2);
        root.right.left = new Node(10, root.right, 1);

        return root;
    }

    private Node getR2WithSmallHeightLeft() {
        Node root = new Node(9, null, 1);

        return root;
    }

    @Test
    public void mergeWithRootLeft() {
        Node root = new Node(8, null, 1);
        this.tree.mergeWithRoot(this.getR1Left(), this.getR2Left(), root);

        Integer[] expected = new Integer[] {8, 3, 9, 2, 5, 11, 1, 4, 7, 10, 6};
        assertArrayEquals(expected, this.tree.printBfs(root));
    }

    @Test
    public void mergeLeft() {
        Integer[] expected = new Integer[] {8, 3, 9, 2, 5, 11, 1, 4, 7, 10, 6};
        assertArrayEquals(expected, this.tree.printBfs(this.tree.merge(this.getR1WithRootLeft(), this.getR2Left())));
    }

    @Test
    public void avlTreeMergeWithRootBigLeft() {
        Node result = this.tree.avlMerge(this.getR1WithRootLeft(), this.getR2WithSmallHeightLeft());

        Integer[] expectedKey = new Integer[]{3, 2, 7, 1, 5, 8, 4, 6, 9};
        Integer[] expectedHeight = new Integer[]{4, 2, 3, 1, 2, 2, 1, 1, 1};
        assertArrayEquals(expectedKey, this.tree.printBfs(result));
        assertArrayEquals(expectedHeight, this.tree.printBfsHeight(result));
    }

    private Node getR1Right() {
        Node root = new Node(1, null, 2);
        root.right = new Node(2, root, 1);

        return root;
    }

    private Node getR1WithRootRight() {
        Node root = new Node(1, null, 3);
        root.right = new Node(3, root, 2);
        root.right.left = new Node(2, root.right, 1);

        return root;
    }

    private Node getR2Right() {
        Node root = new Node(6, null, 5);
        Node left = root.left = new Node(5, root, 2);
        Node right = root.right = new Node(8, root, 4);
        left.left = new Node(4, left, 1);
        right.left = new Node(7, right, 1);
        Node rr = right.right = new Node(11, right, 3);
        rr.left = new Node(10, rr, 2);
        rr.left.left = new Node(9, rr.left, 1);

        return root;
    }

    @Test
    public void mergeWithRootRight() {
        Node root = new Node(3, null, 1);
        this.tree.mergeWithRoot(this.getR1Right(), this.getR2Right(), root);

        Integer[] expected = new Integer[]{3, 1, 6, 2, 5, 8, 4, 7, 11, 10, 9};
        assertArrayEquals(expected, this.tree.printBfs(root));
    }

    @Test
    public void test_mergeRight(){
        Integer[] expected = new Integer[]{3, 1, 6, 2, 5, 8, 4, 7, 11, 10, 9};
        assertArrayEquals(expected, this.tree.printBfs(this.tree.merge(this.getR1WithRootRight(), this.getR2Right())));
    }

    @Test
    public void test_avlTreeMergeWithRootBigRight() {
        Node result = this.tree.avlMerge(this.getR1WithRootRight(), this.getR2Right());

        Integer[] expected = new Integer[]{6, 3, 8, 1, 5, 7, 11, 2, 4, 10, 9};
        Integer[] expectedHeight = new Integer[]{5, 3, 4, 2, 2, 1, 3, 1, 1, 2, 1};
        assertArrayEquals(expected, this.tree.printBfs(result));
        assertArrayEquals(expectedHeight, this.tree.printBfsHeight(result));
    }

    @Test
    public void split() {
        Node root = new Node(20, null, 6);
        Node left = root.left = new Node(8, root, 5);
        root.right = new Node(22, root, 1);
        left.left = new Node(5, left, 1);
        Node right = left.right = new Node(12, left, 4);
        right.left = new Node(9, right, 1);
        Node rr = right.right = new Node(16, right, 3);
        Node rrl = rr.left = new Node(14, rr, 2);
        rr.right = new Node(17, rr, 1);
        rrl.left = new Node(13, rrl, 1);
        rrl.right = new Node(15, rrl, 1);

        Map<String, Node> result = this.tree.split(root, 14);
        Integer[] r1 = new Integer[]{8,5,12,9,13};
        Integer[] r2 = new Integer[]{20,16,22,15,17};
        assertArrayEquals(r1, this.tree.printBfs(result.get("r1")));
        assertArrayEquals(r2, this.tree.printBfs(result.get("r2")));
    }

    @Test
    public void avlSplit() {
        Node root = new Node(20, null, 6);
        Node left = root.left = new Node(8, root, 5);
        root.right = new Node(22, root, 1);
        left.left = new Node(5, left, 1);
        Node right = left.right = new Node(12, left, 4);
        right.left = new Node(9, right, 1);
        Node rr = right.right = new Node(16, right, 3);
        Node rrl = rr.left = new Node(14, rr, 2);
        rr.right = new Node(17, rr, 2);
        rr.right.right = new Node(18, rr.right, 1);
        rrl.left = new Node(13, rrl, 1);
        rrl.right = new Node(15, rrl, 1);

        Map<String, Node> map = this.tree.avlSplit(root, 14);
        Integer[] r1 = new Integer[]{8,5,12,9,13};
        Integer[] r2 = new Integer[]{17,16,20,15,18,22};
        assertArrayEquals(r1, this.tree.printBfs(map.get("r1")));
        assertArrayEquals(r2, this.tree.printBfs(map.get("r2")));
    }
}
