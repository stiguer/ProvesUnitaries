package data;

/**
 * The personal identifying code in the National Health Service.
 */

public class HealthCardID {
    private final String personalID;

    public HealthCardID(String code) {
        if (code == null){throw new RuntimeException("Error: El codi es nul");}
        for (int i=0; i<10; i++)
        {
            if (i<=2 && !Character.isLetter(code.charAt(i)))
            {
                throw new RuntimeException("Error: Codi d'identificació mal format");
            }
            else if (i>2 && !Character.isDigit(code.charAt(i)))
            {
                throw new RuntimeException("Error: Codi d'identificació mal format");
            }
        }
        this.personalID = code;
    }

    public String getPersonalID() { return personalID; }

    @Override
    public boolean equals(Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCardID hcardID = (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }

    @Override
    public int hashCode() { return personalID.hashCode(); }

    @Override
    public String toString() {
        return "HealthCardID{" + "personal code='" + personalID + '\'' + '}';
    }
}
