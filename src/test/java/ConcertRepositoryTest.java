import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcertRepositoryTest {

    @Test
    void constructor() {
        // repository can hold up to 3 concerts
        ConcertRepository repository = new ConcertRepository(3);

        // current size is 0 since no concerts have been added
        assertEquals(0, repository.getCurrentSize());
    }

    @Test
    void addGet1() {
        // repository can hold up to 3 concerts
        ConcertRepository repository = new ConcertRepository(3);
        assertEquals(0, repository.getCurrentSize());

        // add a concert
        assertTrue(repository.add(new Concert("Artist0", 1000)));
        assertEquals(1, repository.getCurrentSize());

        // retrieve the concert using index 0
        Concert c = repository.get(0);
        assertEquals("Artist0", c.getPerformer());
        assertEquals(1000, c.getAvailable());
        assertEquals(0, c.getWaitlist());

    }

    @Test
    void addGet5() {
        // repository can hold up to 5 concerts
        ConcertRepository repository = new ConcertRepository(5);
        assertEquals(0, repository.getCurrentSize());

        // add 5 concerts
        for (int i = 0; i < 5; i++) {
            // add the concert
            assertTrue(repository.add(new Concert("Artist" + i, i * 1000)));

            // retrieve the concert using index i
            assertNotNull(repository.get(i));

            // confirm the current size
            assertEquals(i + 1, repository.getCurrentSize());
        }

        // array is full, can't add another concert
        assertFalse(repository.add(new Concert("Another artist", 100)));

        // confirm the size did not increase
        assertEquals(5, repository.getCurrentSize());
    }

    @Test
    void getConcertState() {
        // array can hold up to 3 concerts
        ConcertRepository repository = new ConcertRepository(3);

        // add 3 concerts
        assertTrue(repository.add(new Concert("The Weeknd", 1000)));
        assertTrue(repository.add(new Concert("Taylor Swift", 500)));
        assertTrue(repository.add(new Concert("Harry Styles", 20000)));
        assertEquals(3, repository.getCurrentSize());

        // confirm each concert was inserted in the correct array position
        assertEquals("The Weeknd", repository.get(0).getPerformer());
        assertEquals("Taylor Swift", repository.get(1).getPerformer());
        assertEquals("Harry Styles", repository.get(2).getPerformer());
    }

    @Test
    public void getOutOfBounds() {
        // array can hold up to 3 concerts
        ConcertRepository repository = new ConcertRepository(3);
        assertTrue(repository.add(new Concert("artist1", 1000)));
        assertTrue(repository.add(new Concert("artist2", 1000)));
        assertTrue(repository.add(new Concert("artist3", 1000)));

        // test that out of bounds index returns null
        assertNull(repository.get(-1));
        assertNull(repository.get(3));
    }


    @Test
    void findByPerformer() {
        ConcertRepository repository = new ConcertRepository(2);

        repository.add(new Concert("Taylor Swift", 1000));
        repository.add(new Concert("The Weeknd", 500));

        Concert c1 = repository.findByPerformer("Taylor Swift");
        assertEquals("Taylor Swift", c1.getPerformer());
        assertEquals(1000, c1.getAvailable());
        assertEquals(0, c1.getWaitlist());

        Concert c2 = repository.findByPerformer("The Weeknd");
        assertEquals("The Weeknd", c2.getPerformer());
        assertEquals(500, c2.getAvailable());
        assertEquals(0, c2.getWaitlist());

        //unknown performer
        Concert c3 = repository.findByPerformer("Unknown Singer");
        assertNull(c3);

    }

    @Test
    void caseInsensitiveFind() {
        ConcertRepository repository = new ConcertRepository(2);

        repository.add(new Concert("Taylor Swift", 1000));
        repository.add(new Concert("The Weeknd", 500));

        Concert c1 = repository.findByPerformer("TAYLOR swift");
        assertEquals("Taylor Swift", c1.getPerformer());
        assertEquals(1000, c1.getAvailable());
        assertEquals(0, c1.getWaitlist());

    }
}