package hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashSetTest {
    public class Phone {
        public String number;

        public String name;

        public Phone(String number, String name) {
            this.number = number;
            this.name = name;
        }
        @Override
        public int hashCode() {
            return Integer.parseInt(number.substring(0, 3));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Phone) {
                return this.number.equals( ((Phone) obj).number );
            }
            return false;
        }
    }

    HashSet<Phone> set;

    @BeforeEach
    public void setUp() {
        set = new HashSet<Phone>();
    }

    @Test
    public void find() {
        Phone phone1 = new Phone("017-0774-1234-5678", "Maria");
        Phone phone2 = new Phone("112-4567-1122-9740", "Sasha");
        Phone phone3 = new Phone("150-2570-7171-7575", "Helen");
        this.set.add(phone1);
        this.set.add(phone2);
        this.set.add(phone3);

        assertTrue(this.set.find(phone1));
        assertTrue(this.set.find(phone2));
        assertTrue(this.set.find(phone3));
        assertFalse(this.set.find(new Phone("123", "test")));
    }

    @Test
    public void remove() {
        Phone phone1 = new Phone("017-0774-1234-5678", "Maria");
        Phone phone2 = new Phone("112-4567-1122-9740", "Sasha");
        Phone phone3 = new Phone("150-2570-7171-7575", "Helen");
        this.set.add(phone1);
        this.set.add(phone2);
        this.set.add(phone3);

        assertEquals("Maria", this.set.remove(phone1).name);
        assertEquals("Sasha", this.set.remove(phone2).name);
        assertEquals("Helen", this.set.remove(phone3).name);
        assertNull(this.set.remove(new Phone("123", "test")));
    }
}
