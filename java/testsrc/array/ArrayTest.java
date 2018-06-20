package array;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import array.Array.*;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {

    Array<String> array;

    @BeforeEach
    void setUp() {
        array = new Array<String>();
    }

    @Test
    void addLast() {
        String expected = "[first]";
        array.addLast("first");

        assertEquals(expected, array.toString());
    }

    @Test
    void add() {
        String expected = "[first,third,second]";
        array.addLast("first");
        array.addLast("second");
        array.add(1, "third");

        assertEquals(expected, array.toString());
    }

    @Test
    void addFirst() {
        String expected = "[fourth,first,third,second]";
        array.addLast("first");
        array.addLast("second");
        array.add(1, "third");
        array.addFirst("fourth");

        assertEquals(expected, array.toString());
    }

    @Test
    void remove() {
        array.addLast("first");
        array.addLast("second");

        String expected = "second";
        String result = array.remove(1);

        assertEquals(expected, result);
    }

    @Test
    void removeFirst() {
        array.addLast("first");
        array.addLast("second");

        String expected = "first";
        String result = array.removeFirst();

        assertEquals(expected, result);
    }

    @Test
    void removeLast() {
        array.addLast("first");
        array.addLast("second");

        String expected = "second";
        String result = array.removeLast();

        assertEquals(expected, result);
    }

    @Test
    void get() {
        array.addLast("first");

        String expected = "first";
        String result = array.get(0);

        assertEquals(expected, result);
    }

    @Test
    void size() {
        assertEquals(0, array.size());

        array.addLast("first");
        assertEquals(1, array.size());
    }

    @Test
    void indexOf() {
        array.addLast("first");
        array.addLast("second");

        assertEquals(1, array.indexOf("second"));
    }

    @Test
    void listIterator() {
        ListIterator iterator = array.listIterator();
        iterator.add("first");
        iterator.add("second");
        iterator.remove();
        iterator.add("third");

        StringBuffer result1 = new StringBuffer();
        while(iterator.hasPrevious()) {
            result1.append(iterator.previous());
        }
        assertEquals("thirdfirst", result1.toString());

        StringBuffer result2 = new StringBuffer();
        while(iterator.hasNext()) {
            result2.append(iterator.next());
        }
        assertEquals("firstthird", result2.toString());

    }
}