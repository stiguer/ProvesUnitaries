package data;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PatientContrTest {
    PatientContr pr1 = new PatientContr(new BigDecimal("3.0"));
    PatientContr pr2 = new PatientContr(new BigDecimal("7.0"));
    PatientContr pr3 = new PatientContr(new BigDecimal("3.0"));

    @Test
    void getPrice() { assertEquals(new BigDecimal("3.0") , pr1.getPrice()); }

    @Test
    void equals() {
        assertFalse(pr1.equals(pr2));
        assertTrue(pr1.equals(pr3));
    }

    @Test
    void hashCodeTest() {
        assertFalse(pr1.hashCode()==pr2.hashCode());
        assertTrue(pr1.hashCode()==pr3.hashCode());
    }

    @Test
    void toStringTest() {
        String s = "PatientContr{price='3.0'}";
        assertEquals(s, pr1.toString());
    }

    @Test
    void PatientContrDNull() {
        assertThrows(RuntimeException.class, () -> pr3 = new PatientContr(null));
    }
}