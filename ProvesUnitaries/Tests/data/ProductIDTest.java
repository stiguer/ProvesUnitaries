package data;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class ProductIDTest {

    ProductID prodID1 = new ProductID("1234567");
    ProductID prodID2 = new ProductID("7654321");
    ProductID prodID3 = new ProductID("1234567");

    @Test
    void getProductID() {
        assertEquals("1234567", prodID1.getProductID());
    }

    @Test
    void equals() {
        assertFalse(prodID1.equals(prodID2));
        assertTrue(prodID1.equals(prodID3));
    }

    @Test
    void hashCodeTest() {
        assertFalse(prodID1.hashCode()==prodID2.hashCode());
        assertTrue(prodID1.hashCode()==prodID3.hashCode());
    }

    @Test
    void toStringTest() {
        String s = "ProductID{product code='1234567'}";
        assertEquals(s, prodID1.toString());
    }

    @Test
    void ProductIDNull() {
        assertThrows(RuntimeException.class, () -> prodID3 = new ProductID(null));
    }
}