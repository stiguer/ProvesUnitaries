package pharmacy;

import data.ProductID;
import exceptions.DispensingNotAvailableException;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * A class that represents the period for dispensing a certain set of
 * medicines, its state and the set of medicines
 */

public class Dispensing {

    private byte nOrder; // n. of order for this dispensing inside the treatment
    private Date initDate, finalDate; // The period
    private boolean isCompleted = false;
    HashMap<ProductID, Boolean> medicines;

    public Dispensing(byte nOrder, Date initDate, Date finalDate){
        this.nOrder = nOrder;
        this.initDate = initDate;
        this.finalDate = finalDate;
    }
    public boolean dispensingEnabled() throws DispensingNotAvailableException {
        Date currentDate = new Date();
        if(currentDate.before(this.initDate)){
            throw new DispensingNotAvailableException("data actual posterior a data inici");
        }
        return true;
    }
    public void setProductAsDispensed(ProductID prodID) { medicines.put(prodID,true); }
    public void setCompleted() { this.isCompleted = true; }

}
