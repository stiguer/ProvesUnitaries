package Terminal;

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;
import exceptions.*;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;
import pharmacy.Sale;
import services.HealthCardReader;
import services.NationalHealthService;

import java.math.BigDecimal;
import java.net.ConnectException;

public class DispensingTerminal {

    private Dispensing dispensing;
    private PatientContr patCont;
    private HealthCardID hcId;
    private Sale sale;
    HealthCardReader hc;
    NationalHealthService sns;

    public DispensingTerminal(){
        this.hc = null;
        this.sns = null;
    }

    public void getePrescription(char option) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        this.hcId = hc.getHealthCardID();
        this.dispensing = sns.getePrescription(hcId);
        this.patCont = sns.getPatientContr(hcId);
    }

    public void initNewSale() throws DispensingNotAvailableException {
        if(!this.dispensing.dispensingEnabled()){
            throw new DispensingNotAvailableException("data actual anterior a data inici");
        }
        this.sale = new Sale(dispensing.getnOrder());
    }

    public void enterProduct(ProductID pID) throws ProductIDException, ConnectException, SaleClosedException {
        ProductSpecification ps = sns.getProductSpecific(pID);
        this.sale.addLine(pID,ps.getPrice(),this.patCont);
        this.dispensing.setProductAsDispensed(pID);
    }
    public void finalizeSale() throws SaleClosedException {
        this.sale.calculateFinalAmount();
        if(!this.dispensing.isCompleted()) {this.dispensing.setCompleted();}
    }
    public Dispensing getDispensing(){ return this.dispensing;}
    public PatientContr getPatCont() { return this.patCont;}
    public Sale getSale() { return this.sale;}
    public void realizePayment(BigDecimal quantity) {}
    public void realizePayment() {}
    public void printNextDispensingSheet() {}
}
