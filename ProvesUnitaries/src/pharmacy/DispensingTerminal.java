package pharmacy;

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;
import exceptions.*;
import services.HealthCardReader;
import services.NationalHealthService;

import java.math.BigDecimal;
import java.net.ConnectException;

public class DispensingTerminal {

    private Sale sale;
    private PatientContr patCont;
    Dispensing dispensing;
    HealthCardReader hc;
    NationalHealthService sns;

    public DispensingTerminal(PatientContr patCont, Dispensing dispensing){
        this.patCont = patCont;
        this.dispensing = dispensing;
    }

    public void getePrescription(char option) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        HealthCardID hcId = hc.getHealthCardID();
        this.dispensing = sns.getePrescription(hcId);
        this.patCont = sns.getPatientContr(hcId);
    }

    public void initNewSale() {
        this.sale = new Sale(1);
    }

    public void enterProduct(ProductID pID) throws ProductIDException, ConnectException, SaleClosedException {
        ProductSpecification ps = sns.getProductSpecific(pID);
        this.sale.addLine(pID,ps.getPrice(),this.patCont);
        this.dispensing.setProductAsDispensed(pID);
    }
    public void finalizeSale() throws SaleClosedException {
        this.sale.calculateFinalAmount();
    }
    public void realizePayment(BigDecimal quantity) {}
    public void realizePayment() {}
    public void printNextDispensingSheet() {}
}
