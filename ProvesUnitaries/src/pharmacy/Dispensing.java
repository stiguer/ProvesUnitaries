package pharmacy;

import data.ProductID;
import exceptions.DispensingNotAvailableException;

import java.util.*;

/**
 * A class that represents the period for dispensing a certain set of
 * medicines, its state and the set of medicines
 */

public class Dispensing {

    private byte nOrder; // n. of order for this dispensing inside the treatment
    private Date initDate, finalDate; // The period
    private boolean isCompleted;
    HashMap<ProductID, Boolean> acquired; // <ProductID, acquired>

    public Dispensing(byte nOrder, Date initDate, Date finalDate, Set<ProductID> medicines ){
        this.nOrder = nOrder;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.isCompleted = false;
        this.acquired = new HashMap<>();

        Iterator<ProductID> it = medicines.iterator();
        while (it.hasNext()){
            acquired.put(it.next(), false);
        }
    }
    public boolean dispensingEnabled() throws DispensingNotAvailableException {
        Date currentDate = new Date();
        if(currentDate.before(this.initDate)){
            throw new DispensingNotAvailableException("data actual anterior a data inici");
        }
        return true;
    }
    public void setProductAsDispensed(ProductID prodID) { acquired.put(prodID,true); }
    public void setCompleted() { this.isCompleted = true; }
    public boolean isCompleted() { return this.isCompleted; }
    public byte getnOrder() { return this.nOrder; }
}
