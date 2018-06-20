package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import array.DoublyLinkedList.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    DoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<Integer>();
    }

    @Test
    void addFirst() {
        list.addFirst(1);
        list.addFirst(2);

        assertEquals("[2,1]", list.toString());
    }

    @Test
    void addLast() {
        list.addLast(1);
        list.addLast(2);

        assertEquals("[1,2]", list.toString());
    }

    @Test
    void add() {
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);

        assertEquals("[1,2,3]", list.toString());
    }

    @Test
    void removeFirst() {
        assertEquals(null, list.removeFirst());

        list.add(0, 1);
        list.add(1, 2);
        assertEquals(Integer.valueOf(1), list.removeFirst());
        assertEquals(Integer.valueOf(2), list.removeFirst());
    }

    @Test
    void remove() {
        assertEquals(null, list.remove(1));

        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);

        assertEquals(null, list.remove(6));
        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(Integer.valueOf(3), list.remove(1));
        assertEquals(Integer.valueOf(1), list.remove(0));
    }

    @Test
    void removeLast() {
        assertEquals(null, list.removeLast());

        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);

        assertEquals(Integer.valueOf(3), list.removeLast());
        assertEquals(Integer.valueOf(2), list.removeLast());
        assertEquals(Integer.valueOf(1), list.removeLast());
        assertEquals(null, list.removeLast());
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
        list.addFirst(2);

        assertEquals(Integer.valueOf(2), list.get(0));
        assertEquals(Integer.valueOf(1), list.get(1));
        assertEquals(null, list.get(2));
    }

    @Test
    void indexOf() {
        assertEquals(-1, list.indexOf(1));

        list.addFirst(1);
        list.addFirst(2);
        assertEquals(1, list.indexOf(1));
        assertEquals(-1, list.indexOf(3));
    }

    @Test
    void listIterator() {
        ListIterator iter = list.listIterator();

        iter.add(1);
        iter.add(2);
        iter.add(3);

        StringBuffer prev = new StringBuffer();
        while(iter.hasPrevious()) {
            prev.append(iter.previous());
        }
        assertEquals("321", prev.toString());

        StringBuffer next = new StringBuffer();
        while(iter.hasNext()) {
             next.append(iter.next());
        }
        assertEquals("123", next.toString());

        iter.previous();
        iter.remove();

        assertEquals(Integer.valueOf(2), iter.previous());
    }
}