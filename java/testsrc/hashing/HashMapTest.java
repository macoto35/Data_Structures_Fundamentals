package hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {

    HashMap map;

    @BeforeEach
    public void setUp() {
        map = new HashMap();
    }

    @Test
    public void hash() {
        assertEquals(17, this.map.hash("017-0774-1234-5678"));
        assertEquals(112, this.map.hash("112-4567-1122-9740"));
        assertEquals(150, this.map.hash("150-2570-7171-7575"));
    }

    @Test
    public void hashKey() {
        this.map.set("017-0774-1234-5678", "Maria");
        this.map.set("112-4567-1122-9740", "Sasha");
        this.map.set("150-2570-7171-7575", "Helen");

        assertTrue(this.map.hashKey("017-0774-1234-5678"));
        assertTrue(this.map.hashKey("112-4567-1122-9740"));
        assertTrue(this.map.hashKey("150-2570-7171-7575"));
        assertFalse(this.map.hashKey("250-2570-7171-7575"));
    }

    @Test
    public void get() {
        this.map.set("017-0774-1234-5678", "Maria");
        this.map.set("112-4567-1122-9740", "Sasha");
        this.map.set("150-2570-7171-7575", "Helen");
        this.map.set("150-2570-7171-7575", "Mery");

        assertEquals("Maria", this.map.get("017-0774-1234-5678"));
        assertEquals("Sasha", this.map.get("112-4567-1122-9740"));
        assertEquals("Mery", this.map.get("150-2570-7171-7575"));
        assertNull(this.map.get("250-2570-7171-7575"));
    }

    @Test
    public void remove() {
        this.map.set("017-0774-1234-5678", "Maria");
        this.map.set("112-4567-1122-9740", "Sasha");
        this.map.set("150-2570-7171-7575", "Helen");

        assertEquals("Maria", this.map.remove("017-0774-1234-5678"));
        assertEquals("Sasha", this.map.remove("112-4567-1122-9740"));
        assertEquals("Helen", this.map.remove("150-2570-7171-7575"));
        assertNull(this.map.remove("250-2570-7171-7575"));
    }
}
