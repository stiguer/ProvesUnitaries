package pharmacy;

import data.PatientContr;
import data.ProductID;
import exceptions.SaleClosedException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Package for the classes involved in the use case Suply next dispensing
 */

public class Sale { // A class that represents the sale of medicines

    private int saleCode;
    private Date date;
    private BigDecimal amount;
    private boolean isClosed; // flag to know if the sale is closed
    private BigDecimal subtotal;

    public Sale(int saleCode) {
        this.saleCode = saleCode;
        this.date = new Date();
        this.subtotal = new BigDecimal("0");
        this.amount = new BigDecimal("0");
        this.isClosed = false;
    }

    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException {
        if(isClosed()){
            throw new SaleClosedException("Venda tancada");
        }
        this.subtotal = this.subtotal.add(price.multiply(contr.getAport()));
    }
    private void calculateAmount() {
        this.amount = this.subtotal;
    }
    private void addTaxes() throws SaleClosedException {
        if(isClosed()){
            throw new SaleClosedException("Venda tancada");
        }
        BigDecimal imp = this.amount.multiply(new BigDecimal("0.21")); // afegir IVA
        this.amount = this.amount.add(imp);
    }
    public void calculateFinalAmount() throws SaleClosedException {
        if(this.isClosed()){
            throw new SaleClosedException("Venda tancada");
        }
        calculateAmount();
        addTaxes();
        setClosed();
    }
    public BigDecimal getAmount() { return this.amount; }
    private void setClosed() { this.isClosed = true;}
    public boolean isClosed() { return this.isClosed; }
    public BigDecimal getSubtotal() {return this.subtotal;}
}
