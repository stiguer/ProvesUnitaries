package data;

import java.math.BigDecimal;

public class PatientContr {
    private final BigDecimal aport;

    public PatientContr(BigDecimal ap) {
        if (ap == null){throw new RuntimeException("Error: La aportació del pacient es nula");}
        this.aport = ap;
    }

    public BigDecimal getAport() { return aport; }

    @Override
    public boolean equals(Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientContr p = (PatientContr) o;
        return aport.equals(p.aport);
    }

    @Override
    public int hashCode() { return aport.hashCode(); }

    @Override
    public String toString() {
        return "PatientContr{" + "aportació='" + aport + '\'' + '}';
    }


}
