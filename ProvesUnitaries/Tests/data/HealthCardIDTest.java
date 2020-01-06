package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCardIDTest {

    HealthCardID cardID1 = new HealthCardID("ABC1234567");
    HealthCardID cardID2 = new HealthCardID("CDF7654321");
    HealthCardID cardID3 = new HealthCardID("ABC1234567");

    @Test
    void getPersonalID() {
        assertEquals("ABC1234567", cardID1.getPersonalID());
    }

    @Test
    void equals() {
        assertFalse(cardID1.equals(cardID2));
        assertTrue(cardID1.equals(cardID3));
    }

    @Test
    void hashCodeTest() {
        assertFalse(cardID1.hashCode()==cardID2.hashCode());
        assertTrue(cardID1.hashCode()==cardID3.hashCode());
    }

    @Test
    void toStringTest() {
        String s = "HealthCardID{personal code='ABC1234567'}";
        assertEquals(s, cardID1.toString());
    }

    @Test
    void HealthCardIDNull() {
        assertThrows(RuntimeException.class, () -> cardID3 = new HealthCardID(null));
    }
}