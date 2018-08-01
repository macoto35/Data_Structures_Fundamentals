package priorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisJointSetTreeTest {

    private DisJointSetTree set;

    @BeforeEach
    public void setUp() {
        this.set = new DisJointSetTree();
    }

    @Test
    public void makeSet(){
        this.set.makeSet(0);
        this.set.makeSet(1);
        this.set.makeSet(2);
        this.set.makeSet(3);
        this.set.makeSet(4);
        this.set.makeSet(5);

        assertEquals("0,1,2,3,4,5,", this.set.toString());
    }

    @Test
    public void find() {
        this.set.makeSet(0);
        this.set.makeSet(1);
        this.set.makeSet(2);
        this.set.makeSet(3);
        this.set.makeSet(4);
        this.set.makeSet(5);

        assertEquals(0, this.set.find(0));
        assertEquals(1, this.set.find(1));
        assertEquals(2, this.set.find(2));
        assertEquals(3, this.set.find(3));
        assertEquals(4, this.set.find(4));
        assertEquals(5, this.set.find(5));

        assertThrows(IndexOutOfBoundsException.class, () -> this.set.find(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> this.set.find(6));
    }

    @Test
    public void union() {
        this.set.makeSet(0);
        this.set.makeSet(1);
        this.set.makeSet(2);
        this.set.makeSet(3);
        this.set.makeSet(4);
        this.set.makeSet(5);

        this.set.union(1, 3);
        assertEquals("0,3,2,3,4,5,", this.set.toString());

        this.set.union(4, 1);
        assertEquals("0,3,2,3,3,5,", this.set.toString());

        this.set.union(2, 0);
        assertEquals("0,3,0,3,3,5,", this.set.toString());

        this.set.union(1, 2);
        assertEquals("0,3,0,0,3,5,", this.set.toString());

        this.set.union(1, 5);
        assertEquals("0,3,0,0,3,0,", this.set.toString());
    }
}
