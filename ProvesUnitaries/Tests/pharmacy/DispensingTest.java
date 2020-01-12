package pharmacy;

import data.ProductID;
import exceptions.DispensingNotAvailableException;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DispensingTest {
    Date date1 = new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime();
    Date date2 = new GregorianCalendar(2040, Calendar.FEBRUARY, 11).getTime();
    Date date3 = new GregorianCalendar(2050, Calendar.FEBRUARY, 11).getTime();
    Byte nOrder = 1;
    Set<ProductID> medicines = new HashSet<>();
    ProductID prod1 = new ProductID("prod1234");
    ProductID prod2 = new ProductID("prod4321");

    @Test
    void dispensingEnabled() throws DispensingNotAvailableException {
        Dispensing dispensing1 = new Dispensing(nOrder,date1,date2,medicines);
        Dispensing dispensing2 = new Dispensing(nOrder,date2,date3,medicines);
        assertTrue(dispensing1.dispensingEnabled()); // data actual posterior a data inicial
        assertThrows(DispensingNotAvailableException.class, () -> dispensing2.dispensingEnabled()); // data actual anterior a data inicial
    }

    @Test
    void setProductAsDispensed() {
        medicines.add(prod1);
        medicines.add(prod2);
        Dispensing dispensing1 = new Dispensing(nOrder,date1,date2,medicines);

        assertFalse(dispensing1.acquired.get(prod1));
        assertFalse(dispensing1.acquired.get(prod2));

        dispensing1.setProductAsDispensed(prod1);
        dispensing1.setProductAsDispensed(prod2);

        assertTrue(dispensing1.acquired.get(prod1));
        assertTrue(dispensing1.acquired.get(prod2));
    }

    @Test
    void setCompleted() {
        medicines.add(prod1);
        medicines.add(prod2);
        Dispensing dispensing1 = new Dispensing(nOrder,date1,date2,medicines);

        assertFalse(dispensing1.isCompleted());
        dispensing1.setCompleted();
        assertTrue(dispensing1.isCompleted());
    }
}