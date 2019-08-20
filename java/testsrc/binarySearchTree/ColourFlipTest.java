package binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ColourFlipTest {
    ColourFlip cf;

    @BeforeEach
    public void setUp() {
        cf = new ColourFlip();
    }

    @Test
    public void newArray() {
        this.cf.newArray(7);

        assertEquals("4,2,6,1,3,5,7", this.cf.bfsPrint(this.cf.t1, "key"));
        assertEquals("3,2,2,1,1,1,1", this.cf.bfsPrint(this.cf.t1, "height"));
        assertEquals("7,3,3,1,1,1,1", this.cf.bfsPrint(this.cf.t1, "size"));
        assertEquals("w,w,w,w,w,w,w", this.cf.bfsPrint(this.cf.t1, "colour"));

        assertEquals("4,2,6,1,3,5,7", this.cf.bfsPrint(this.cf.t2, "key"));
        assertEquals("3,2,2,1,1,1,1", this.cf.bfsPrint(this.cf.t2, "height"));
        assertEquals("7,3,3,1,1,1,1", this.cf.bfsPrint(this.cf.t2, "size"));
        assertEquals("b,b,b,b,b,b,b", this.cf.bfsPrint(this.cf.t2, "colour"));
    }

    @Test
    public void merge() {
        this.cf.t1 = this.cf.avlInsert(this.cf.t1, 1, 'w');
        this.cf.t1 = this.cf.avlInsert(this.cf.t1, 2, 'w');
        this.cf.t1 = this.cf.avlInsert(this.cf.t1, 3, 'w');
        this.cf.t2 = this.cf.avlInsert(this.cf.t2, 4, 'w');
        this.cf.t2 = this.cf.avlInsert(this.cf.t2, 5, 'w');
        this.cf.t2 = this.cf.avlInsert(this.cf.t2, 6, 'w');
        assertEquals("3,2,5,1,4,6", this.cf.bfsPrint(this.cf.merge(this.cf.t1, this.cf.t2), "key"));
    }

    @Test
    public void split() {
        Node root = this.cf.avlInsert(null, 1, 'w');
        root = this.cf.avlInsert(root, 2, 'w');
        root = this.cf.avlInsert(root, 3, 'w');
        root = this.cf.avlInsert(root, 4, 'w');
        root = this.cf.avlInsert(root, 5, 'w');
        root = this.cf.avlInsert(root, 6, 'w');
        root = this.cf.avlInsert(root, 7, 'w');

        Map<String, Node> result = this.cf.split(root, 5);
        Node r1 = result.get("r1");
        Node r2 = result.get("r2");

        assertEquals("4,2,1,3", this.cf.bfsPrint(r1, "key"));
        assertEquals("6,5,7", this.cf.bfsPrint(r2, "key"));
    }

    /*public void flip() {
        this.cf.newArray(7);

        this.cf.flip(5);
        assertEquals("w,w,b,w,w,b,b", this.cf.bfsPrint(this.cf.t1, "colour"));
        assertEquals("b,b,w,b,b,w,w", this.cf.bfsPrint(this.cf.t2, "colour"));

        this.cf.flip(2);
        assertEquals("b,b,w,w,b,w,w", this.cf.bfsPrint(this.cf.t1, "colour"));
        assertEquals("w,w,b,b,w,b,b", this.cf.bfsPrint(this.cf.t2, "colour"));

        // err case
        this.cf.flip(4);
        assertEquals("w,b,b,w,b,b,b", this.cf.bfsPrint(this.cf.t1, "key"));
        assertEquals("w,b,b,w,b,b,b", this.cf.bfsPrint(this.cf.t1, "colour"));
        assertEquals("b,w,w,b,w,w,w", this.cf.bfsPrint(this.cf.t2, "colour"));
    }*/
}
