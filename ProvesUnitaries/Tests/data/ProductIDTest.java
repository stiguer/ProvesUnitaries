package data;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class ProductIDTest {

    ProductID prodID1 = new ProductID("prod1234");
    ProductID prodID2 = new ProductID("prod4321");
    ProductID prodID3 = new ProductID("prod1234");

    @Test
    void getProductID() {
        assertEquals("prod1234", prodID1.getProductID());
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
        String s = "ProductID{product code='prod1234'}";
        assertEquals(s, prodID1.toString());
    }

    @Test
    void ProductIDNull() {
        assertThrows(RuntimeException.class, () -> prodID3 = new ProductID(null));
    }
}