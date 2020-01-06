package data;

import java.math.BigDecimal;

public class PatientContr {
    private final BigDecimal price;

    public PatientContr(BigDecimal pr) {
        if (pr == null){throw new RuntimeException("Error: es nul");}
        this.price = pr;
    }

    public BigDecimal getPrice() { return price; }

    @Override
    public boolean equals(Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientContr p = (PatientContr) o;
        return price.equals(p.price);
    }

    @Override
    public int hashCode() { return price.hashCode(); }

    @Override
    public String toString() {
        return "PatientContr{" + "price='" + price + '\'' + '}';
    }


}
