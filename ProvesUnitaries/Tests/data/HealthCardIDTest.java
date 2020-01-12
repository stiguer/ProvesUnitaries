package data;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HealthCardIDTest {

    HealthCardID cardID1 = new HealthCardID("123456789012");
    HealthCardID cardID2 = new HealthCardID("987654321098");
    HealthCardID cardID3 = new HealthCardID("123456789012");

    @Test
    void getPersonalID() {
        assertEquals("123456789012", cardID1.getPersonalID());
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
        String s = "HealthCardID{personal code='123456789012'}";
        assertEquals(s, cardID1.toString());

        System.out.print("first statement. ");
    }

    @Test
    void HealthCardIDNull() {
        assertThrows(RuntimeException.class, () -> cardID3 = new HealthCardID(null));
    }
}