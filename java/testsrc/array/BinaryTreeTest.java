package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    BinaryTree tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree();

        TreeNode<String> cathy = new TreeNode<String>("Cathy");
        cathy.setLeft(new TreeNode<String>("Alex"));
        cathy.setRight(new TreeNode<String>("Frank"));

        TreeNode<String> sam = new TreeNode<String>("Sam");
        sam.setLeft(new TreeNode<String>("Nancy"));
        TreeNode<String> violet = new TreeNode<String>("Violet");
        violet.setLeft(new TreeNode<String>("Tony"));
        violet.setRight(new TreeNode<String>("Wendy"));
        sam.setRight(violet);

        TreeNode<String> les = new TreeNode<String>("Les");
        les.setLeft(cathy);
        les.setRight(sam);
        tree.setRoot(les);
    }

    @Test
    void depthFirstPreOrderTraverse() {
        assertEquals("Les, Cathy, Alex, Frank, Sam, Nancy, Violet, Tony, Wendy, ", tree.depthFirstPreOrderTraverse());
    }

    @Test
    void depthFirstInOrderTraverse() {
        assertEquals("Alex, Cathy, Frank, Les, Nancy, Sam, Tony, Violet, Wendy, ", tree.depthFirstInOrderTraverse());
    }

    @Test
    void depthFirstPostOrderTraverse() {
        assertEquals("Alex, Frank, Cathy, Nancy, Tony, Wendy, Violet, Sam, Les, ", tree.depthFirstPostOrderTraverse());
    }

    @Test
    void breathFirst() {
        assertEquals("Les, Cathy, Sam, Alex, Frank, Nancy, Violet, Tony, Wendy, ", tree.breathFirst());
    }
}