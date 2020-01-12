package pharmacy;

import data.PatientContr;
import data.ProductID;
import exceptions.SaleClosedException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    Sale sale = new Sale(1234567);
    ProductID p1 = new ProductID("prod1234");
    ProductID p2 = new ProductID("prod4321");
    ProductID p3 = new ProductID("prod9874");
    ProductID p4 = new ProductID("prod7896");
    BigDecimal price1 = new BigDecimal("3");
    BigDecimal price2 = new BigDecimal("10.5");
    BigDecimal price3 = new BigDecimal("11.2");
    BigDecimal price4 = new BigDecimal("4.25");
    BigDecimal bd1 = new BigDecimal("0.1");
    PatientContr aport = new PatientContr(bd1);
    //expected values
    BigDecimal bd2 = new BigDecimal("0.3");
    BigDecimal bd3 = new BigDecimal("0.51425");
    BigDecimal bd4 = new BigDecimal("3.50295");
    BigDecimal bd5 = new BigDecimal("0.425");

    @Test
    void addLine() throws SaleClosedException {
        sale.addLine(p1,price1,aport); // subtotal = subtotal + price1 * aport
        assertEquals(bd2,sale.getSubtotal());
    }

    @Test
    void calculateFinalAmount() throws SaleClosedException {
        sale.addLine(p1,price1,aport); // subtotal = subtotal + price1 * aport
        sale.addLine(p2,price2,aport); // subtotal = subtotal + price2 * aport
        sale.addLine(p3,price3,aport); // subtotal = subtotal + price3 * aport
        sale.addLine(p4,price4,aport); // subtotal = subtotal + price4 * aport

        // calcular total i afegir iva
        sale.calculateFinalAmount();
        assertEquals(bd4,sale.getAmount());
    }

    @Test
    void getAmount() throws SaleClosedException {
        sale.addLine(p4,price4,aport); // subtotal = subtotal + price4 * aport
        sale.calculateFinalAmount();
        assertEquals(bd3,sale.getAmount());
    }

    @Test
    void isClosed() throws SaleClosedException {
        assertFalse(sale.isClosed());
        sale.addLine(p4,price4,aport); // subtotal = subtotal + price4 * aport
        assertFalse(sale.isClosed());
        sale.calculateFinalAmount();
        assertTrue(sale.isClosed());
    }

    @Test
    void getSubtotal() throws SaleClosedException {
        sale.addLine(p4,price4,aport); // subtotal = subtotal + price4 * aport
        assertEquals(bd5,sale.getSubtotal());
    }
}