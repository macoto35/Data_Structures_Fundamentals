package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import array.SinglyLinkedList.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    SinglyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<Integer>();
    }

    @Test
    void addFirst() {
        list.addFirst(1);

        assertEquals("[1]", list.toString());
    }

    @Test
    void addLast() {
        list.addLast(1);

        assertEquals("[1]", list.toString());
    }

    @Test
    void add() {
        list.add(1, 3);
        assertEquals("[]", list.toString());

        list.addFirst(1);
        list.addLast(2);

        list.add(1, 3);
        assertEquals("[1,3,2]", list.toString());
    }

    @Test
    void removeFirst() {
        assertEquals(null, list.removeFirst());

        list.addFirst(1);
        assertEquals(Integer.valueOf(1), list.removeFirst());
    }

    @Test
    void remove() {
        assertEquals(null, list.remove(0));

        list.addFirst(1);
        assertEquals(Integer.valueOf(1), list.remove(0));

        list.addFirst(1);
        list.addLast(2);
        assertEquals(Integer.valueOf(2), list.remove(1));

        list.add(1, 2);
        list.addLast(3);
        assertEquals(Integer.valueOf(2), list.remove(1));
    }

    @Test
    void removeLast() {
        assertEquals(null, list.removeLast());

        list.addFirst(1);
        assertEquals(Integer.valueOf(1), list.removeLast());

        list.addFirst(1);
        list.addLast(2);
        assertEquals(Integer.valueOf(2), list.removeLast());
    }

    @Test
    void size() {
        assertEquals(0, list.size());

        list.addFirst(1);
        assertEquals(1, list.size());
    }

    @Test
    void get() {
        assertEquals(null, list.get(0));

        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test
    void indexOf() {
        assertEquals(-1, list.indexOf(2));

        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    void listIterator() {
        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);

        ListIterator iter = list.listIterator();
        StringBuffer sb = new StringBuffer();
        while(iter.hasNext()) {
            sb.append(iter.next());
        }

        assertEquals("123", sb.toString());
    }

}