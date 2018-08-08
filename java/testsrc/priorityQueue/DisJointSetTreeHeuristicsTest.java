package priorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisJointSetTreeHeuristicsTest {

    DisJointSetTreeHeuristics set;

    @BeforeEach
    public void setUp() {
        this.set = new DisJointSetTreeHeuristics();
    }

    @Test
    public void makeSet() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.set.makeSet(-1));

        this.set.makeSet(0);
        this.set.makeSet(1);
        this.set.makeSet(2);
        this.set.makeSet(3);
        this.set.makeSet(4);
        this.set.makeSet(5);
        assertEquals("0|0,1|0,2|0,3|0,4|0,5|0,", this.set.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> this.set.makeSet(6));
    }

    @Test
    public void union() {
        this.set.makeSet(0);
        this.set.makeSet(1);
        this.set.makeSet(2);
        this.set.makeSet(3);
        this.set.makeSet(4);
        this.set.makeSet(5);

        this.set.union(0, 1);
        assertEquals("1|0,1|1,2|0,3|0,4|0,5|0,", this.set.toString());

        this.set.union(1, 2);
        assertEquals("1|0,1|1,1|0,3|0,4|0,5|0,", this.set.toString());

        this.set.union(3, 4);
        assertEquals("1|0,1|1,1|0,4|0,4|1,5|0,", this.set.toString());

        this.set.union(1, 4);
        assertEquals("1|0,4|1,1|0,4|0,4|2,5|0,", this.set.toString());

        this.set.union(0, 5);
        assertEquals("4|0,4|1,1|0,4|0,4|2,4|0,", this.set.toString());
    }
}
