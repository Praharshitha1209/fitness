import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class LessonTest {

    private Lesson lesson;

    @Before
    public void setUp() {
        lesson = new Lesson("Yoga", "Monday", 8, 20);
    }

    @Test
    public void testGetType() {
        assertEquals("Yoga", lesson.getType());
    }

    @Test
    public void testGetDay() {
        assertEquals("Monday", lesson.getDay());
    }

    @Test
    public void testGetTime() {
        assertEquals(8, lesson.getTime());
    }

    @Test
    public void testGetPrice() {
        assertEquals(20, lesson.getPrice());
    }

    @Test
    public void testIsFull() {
        assertFalse(lesson.isFull());
        lesson.addCustomer("Alice");
        lesson.addCustomer("Bob");
        lesson.addCustomer("Charlie");
        lesson.addCustomer("David");
        lesson.addCustomer("Eve");
        assertTrue(lesson.isFull());
    }

    

    @Test
    public void testRemoveCustomer() {
        lesson.addCustomer("Alice");
        lesson.addCustomer("Bob");
        assertTrue(lesson.removeCustomer("Bob"));
        assertEquals(1, lesson.getNumCustomers());
        assertTrue(lesson.removeCustomer("Alice"));
        assertEquals(0, lesson.getNumCustomers());
        assertFalse(lesson.removeCustomer("Charlie")); // cannot remove a non-existing customer
    }

    @Test
    public void testGetCustomers() {
        lesson.addCustomer("Alice");
        lesson.addCustomer("Bob");
        lesson.addCustomer("Charlie");
        assertEquals(3, lesson.getCustomers().size());
        assertTrue(lesson.getCustomers().contains("Alice"));
        assertTrue(lesson.getCustomers().contains("Bob"));
        assertTrue(lesson.getCustomers().contains("Charlie"));
    }

    @Test
    public void testGetRating() {
        assertEquals(0.0, lesson.getRating(), 0.01);
    }

    @Test
    public void testSetRating() {
        lesson.setRating(4.5);
        assertEquals(4.5, lesson.getRating(), 0.01);
    }

   
    @Test
    public void testGetStatus() {
        assertEquals("booked", lesson.getStatus());
    }

    @Test
    public void testSetStatus() {
        lesson.setStatus("cancelled");
        assertEquals("cancelled", lesson.getStatus());
    }

    @Test
    public void testGetNumCustomers() {
        assertEquals(0, lesson.getNumCustomers());
        lesson.addCustomer("Alice");
        assertEquals(1, lesson.getNumCustomers());
        lesson.addCustomer("Bob");
        assertEquals(2, lesson.getNumCustomers());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(5, lesson.getCapacity());
    }
}
