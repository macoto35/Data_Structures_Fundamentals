package hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InternationalPhoneBookTest {
    InternationalPhoneBookLinkedList list;
    InternationalPhoneBookDynamicArray array;

    @BeforeEach
    public void setUp() {
        list = new InternationalPhoneBookLinkedList();
        array = new InternationalPhoneBookDynamicArray();
    }

    @Test
    public void linkedList() {
        list.setName("017-0774-1234-5678", "Maria");
        list.setName("112-4567-1122-9740", "Sasha");
        list.setName("150-2570-7171-7575", "Helen");

        assertEquals("Maria", list.getName("017-0774-1234-5678"));
        assertEquals("Sasha", list.getName("112-4567-1122-9740"));
        assertEquals("Helen", list.getName("150-2570-7171-7575"));
        assertNull(list.getName("140-2570-7171-7575"));
    }

    @Test
    public void dynamicArray() {
        array.setName("017-0774-1234-5678", "Maria");
        array.setName("112-4567-1122-9740", "Sasha");
        array.setName("150-2570-7171-7575", "Helen");

        assertEquals("Maria", array.getName("017-0774-1234-5678"));
        assertEquals("Sasha", array.getName("112-4567-1122-9740"));
        assertEquals("Helen", array.getName("150-2570-7171-7575"));
        assertNull(array.getName("140-2570-7171-7575"));
    }
}
