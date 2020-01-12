package data;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PatientContrTest {
    PatientContr ap1 = new PatientContr(new BigDecimal("3.0"));
    PatientContr ap2 = new PatientContr(new BigDecimal("7.0"));
    PatientContr ap3 = new PatientContr(new BigDecimal("3.0"));

    @Test
    void getAport() { assertEquals(new BigDecimal("3.0") , ap1.getAport()); }

    @Test
    void equals() {
        assertFalse(ap1.equals(ap2));
        assertTrue(ap1.equals(ap3));
    }

    @Test
    void hashCodeTest() {
        assertFalse(ap1.hashCode()==ap2.hashCode());
        assertTrue(ap1.hashCode()==ap3.hashCode());
    }

    @Test
    void toStringTest() {
        String s = "PatientContr{aportació='3.0'}";
        assertEquals(s, ap1.toString());
    }

    @Test
    void PatientContrDNull() {
        assertThrows(RuntimeException.class, () -> ap3 = new PatientContr(null));
    }
}