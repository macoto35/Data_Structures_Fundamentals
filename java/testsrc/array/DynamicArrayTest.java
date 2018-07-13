package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {
    DynamicArray<String> array;

    @BeforeEach
    public void setUp() {
        array = new DynamicArray<String>();
    }

    @Test
    public void pushBack() {
        array.pushBack("a");
	assertAll("push back: a", 
	    () -> assertEquals(1, array.size()),
	    () -> assertEquals(1, array.capacity()),
	    () -> assertEquals("a,", array.toString())
	);

        array.pushBack("b");
	assertAll("push back: b", 
	    () -> assertEquals(2, array.size()),
	    () -> assertEquals(2, array.capacity()),
	    () -> assertEquals("a,b,", array.toString())
	);
        
	array.pushBack("c");
	assertAll("push back: c",
	    () -> assertEquals(3, array.size()),
	    () -> assertEquals(4, array.capacity()),
	    () -> assertEquals("a,b,c,", array.toString())
	);
        
	array.pushBack("d");
	assertAll("push back: d", 
	    () -> assertEquals(4, array.size()),
	    () -> assertEquals(4, array.capacity()),
	    () -> assertEquals("a,b,c,d,", array.toString())
	);
        
	array.pushBack("e");
	assertAll("push back: e", 
	    () -> assertEquals(5, array.size()),
	    () -> assertEquals(8, array.capacity()),
	    () -> assertEquals("a,b,c,d,e,", array.toString())
	);
    }

    @Test
    public void get() {
	assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
        
	array.pushBack("a");
        array.pushBack("b");
        array.pushBack("c");
        array.pushBack("d");
        array.pushBack("e");

	assertEquals("a", array.get(0));
	assertEquals("b", array.get(1));
	assertEquals("c", array.get(2));
	assertEquals("d", array.get(3));
	assertEquals("e", array.get(4));
	assertThrows(IndexOutOfBoundsException.class, () -> array.get(5));
    }

    @Test
    public void set() {
	assertThrows(IndexOutOfBoundsException.class, () -> array.set(0, "a"));
        
	array.pushBack("a");
        array.pushBack("b");
        array.pushBack("c");
        array.pushBack("d");
        array.pushBack("e");

	array.set(1, "b1");
	assertEquals("a,b1,c,d,e,", array.toString());
	array.set(3, "d1");
	assertEquals("a,b1,c,d1,e,", array.toString());
	
	assertThrows(IndexOutOfBoundsException.class, () -> array.set(5, "f"));
    }

    @Test
    public void remove() {
	assertNull(array.remove(0));
	
	array.pushBack("a");
        array.pushBack("b");
        array.pushBack("c");
        array.pushBack("d");
        array.pushBack("e");
	
	assertEquals("a", array.remove(0));
	assertEquals("e", array.remove(3));
	assertEquals("d", array.remove(2));
	assertEquals("b", array.remove(0));
	assertEquals("c", array.remove(0));
        assertNull(array.remove(0));
    }
}
