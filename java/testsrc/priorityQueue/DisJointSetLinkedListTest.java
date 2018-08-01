package priorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisJointSetLinkedListTest {

    private DisJointSetLinkedList set;

    @BeforeEach
    public void setUp() {
        this.set = new DisJointSetLinkedList();
    }

    @Test
    public void makeSet() {
        this.set.makeSet(0);
        this.set.makeSet(1);
        this.set.makeSet(2);
        this.set.makeSet(3);
        this.set.makeSet(4);
        this.set.makeSet(5);
        this.set.makeSet(6);
        this.set.makeSet(7);
        this.set.makeSet(8);

        assertEquals("0,1,2,3,4,5,6,7,8,", this.set.toString());
        assertThrows(IndexOutOfBoundsException.class, () -> set.makeSet(9));
    }

    @Test
    public void find() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.set.find(-1));

        this.set.makeSet(0);
        this.set.makeSet(1);
        this.set.makeSet(2);
        this.set.makeSet(3);
        this.set.makeSet(4);
        this.set.makeSet(5);
        this.set.makeSet(6);
        this.set.makeSet(7);
        this.set.makeSet(8);
        assertEquals(0, this.set.find(0));
        assertEquals(1, this.set.find(1));
        assertEquals(2, this.set.find(2));
        assertEquals(3, this.set.find(3));
        assertEquals(4, this.set.find(4));
        assertEquals(5, this.set.find(5));
        assertEquals(6, this.set.find(6));
        assertEquals(7, this.set.find(7));
        assertEquals(8, this.set.find(8));

        assertThrows(IndexOutOfBoundsException.class, () -> this.set.find(9));
    }

    @Test
    public void union() {
        this.set.makeSet(0);
        this.set.makeSet(1);
        this.set.makeSet(2);
        this.set.makeSet(3);
        this.set.makeSet(4);
        this.set.makeSet(5);
        this.set.makeSet(6);
        this.set.makeSet(7);
        this.set.makeSet(8);

        this.set.union(8, 2);
        assertEquals("0,1,2,3,4,5,6,7,2,", this.set.toString());

        this.set.union(2, 1);
        assertEquals("0,1,1,3,4,5,6,7,1,", this.set.toString());

        this.set.union(1, 3);
        assertEquals("0,3,3,3,4,5,6,7,3,", this.set.toString());

        this.set.union(3, 6);
        assertEquals("0,6,6,6,4,5,6,7,6,", this.set.toString());

        this.set.union(5, 0);
        assertEquals("0,6,6,6,4,0,6,7,6,", this.set.toString());

        this.set.union(0, 7);
        assertEquals("7,6,6,6,4,7,6,7,6,", this.set.toString());
    }
}
