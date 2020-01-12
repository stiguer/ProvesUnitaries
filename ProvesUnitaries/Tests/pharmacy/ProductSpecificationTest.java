package pharmacy;

import data.ProductID;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductSpecificationTest {
    ProductID prodID = new ProductID("prod1234");
    BigDecimal price = new BigDecimal("3");
    ProductSpecification prodS = new ProductSpecification(prodID,"description",price);

    @Test
    void getDescription() {
        assertEquals(price, prodS.getPrice());
    }

    @Test
    void getPrice() {
        assertEquals("description",prodS.getDescription());
    }
}