package pharmacy;

import data.ProductID;
import services.NationalHealthService;

import java.math.BigDecimal;

public class ProductSpecification {

    private ProductID UPCcode;
    private String description;
    private BigDecimal price;

    public ProductSpecification(ProductID code, String des, BigDecimal price){
        this.UPCcode = code;
        this.description = des;
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}
