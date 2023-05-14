import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcertTest {

    private Concert c1, c2;

    @BeforeEach
    void setup() {
         c1 = new Concert("The Weeknd", 10);
         c2 = new Concert("Harry Styles", 2);
    }

    @Test
    void constructor() {
        assertEquals("The Weeknd", c1.getPerformer());
        assertEquals(10, c1.getAvailable());
        assertEquals(0, c1.getWaitlist());

        assertEquals("Harry Styles", c2.getPerformer());
        assertEquals(2, c2.getAvailable());
        assertEquals(0, c2.getWaitlist());
    }

    @Test
    void testToString() {
        assertEquals("Concert{performer='The Weeknd', available=10, waitlist=0}", c1.toString());
        assertEquals("Concert{performer='Harry Styles', available=2, waitlist=0}", c2.toString());
    }

    @Test
    void purchaseTicket() {
        assertEquals(10, c1.getAvailable());
        assertTrue(c1.purchaseTicket());
        assertEquals(9, c1.getAvailable());
        assertTrue(c1.purchaseTicket());
        assertEquals(8, c1.getAvailable());

        assertEquals(2, c2.getAvailable());
        assertTrue(c2.purchaseTicket());
        assertEquals(1, c2.getAvailable());
        assertTrue(c2.purchaseTicket());
        assertEquals(0, c2.getAvailable());
        //no tickets left, remain at 0
        assertFalse(c2.purchaseTicket());
        assertEquals(0, c2.getAvailable());
    }

    @Test
    void addToWaitlist() {
        assertEquals(0, c1.getWaitlist());
        c1.addToWaitlist();
        assertEquals(1, c1.getWaitlist());
        c1.addToWaitlist();
        assertEquals(2, c1.getWaitlist());

        assertEquals(0, c2.getWaitlist());
        c2.addToWaitlist();
        assertEquals(1, c2.getWaitlist());
        c2.addToWaitlist();
        assertEquals(2, c2.getWaitlist());
    }
}