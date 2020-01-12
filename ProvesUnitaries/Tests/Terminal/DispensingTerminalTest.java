package Terminal;

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;
import pharmacy.Sale;
import services.HealthCardReader;
import services.NationalHealthService;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DispensingTerminalTest {
    int saleCode = 123;
    DispensingTerminal dispensingTerminal = new DispensingTerminal(saleCode);

    public static class NationalHealthServiceTestDoble implements NationalHealthService {
        HashMap<ProductID,ProductSpecification> productSpecifications = new HashMap<>();
        HashMap<HealthCardID,PatientContr> pacients = new HashMap<>();
        HashMap<HealthCardID,Dispensing> dispensacions = new HashMap<>();


        @Override
        public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException{
            if(!pacients.containsKey(hcID)){
                throw new HealthCardException("L'identificador del pacient no és incorrecte");
            }
            if(!dispensacions.containsKey(hcID)){
                throw new HealthCardException("El pacient no té cap eRecepta associada");
            }
            return dispensacions.get(hcID);
        }

        @Override
        public PatientContr getPatientContr(HealthCardID hcID) throws ConnectException {
            return pacients.get(hcID);
        }

        @Override
        public ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException {
            if(!productSpecifications.containsKey(pID)){
                throw new ProductIDException("El producte no està al catàleg");
            }
            return productSpecifications.get(pID);
        }

        @Override
        public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
            return null;
        }
    }

    public static class HealthCardReaderTestDoble implements HealthCardReader {
        HashMap<HealthCardID,PatientContr> pacients = new HashMap<>();
        HealthCardID cardID1 = new HealthCardID("123456789012");
        @Override
        public HealthCardID getHealthCardID() throws HealthCardException {
            if(!pacients.containsKey(cardID1)){
                throw new HealthCardException("L'identificador del pacient no és incorrecte");
            }
            return cardID1;
        }
    }

    @BeforeEach
    public void BeforeEach() {
        dispensingTerminal.sns = new NationalHealthServiceTestDoble();
        dispensingTerminal.hc = new HealthCardReaderTestDoble();

        ProductID prodID1 = new ProductID("prod1234");
        ProductID prodID2 = new ProductID("prod4321");
        ProductID prodID3 = new ProductID("prod1234");
        ProductID prodID4 = new ProductID("prod4326");
        ProductID prodID5 = new ProductID("prod1854");
        BigDecimal price = new BigDecimal("3");
        ProductSpecification prodS1 = new ProductSpecification(prodID1,"description1",price);
        ProductSpecification prodS2 = new ProductSpecification(prodID2,"description2",price);
        ProductSpecification prodS3 = new ProductSpecification(prodID3,"description3",price);
        ProductSpecification prodS4 = new ProductSpecification(prodID2,"description4",price);
        ProductSpecification prodS5 = new ProductSpecification(prodID3,"description5",price);
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).productSpecifications.put(prodID1,prodS1);
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).productSpecifications.put(prodID2,prodS2);
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).productSpecifications.put(prodID3,prodS3);
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).productSpecifications.put(prodID4,prodS4);
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).productSpecifications.put(prodID5,prodS5);

        HealthCardID cardID1 = new HealthCardID("123456789012");
        HealthCardID cardID2 = new HealthCardID("987654321098");
        HealthCardID cardID3 = new HealthCardID("123456789012");
        PatientContr ap1 = new PatientContr(new BigDecimal("3.0"));
        PatientContr ap2 = new PatientContr(new BigDecimal("7.0"));
        PatientContr ap3 = new PatientContr(new BigDecimal("3.0"));
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).pacients.put(cardID1,ap1);
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).pacients.put(cardID2,ap2);
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).pacients.put(cardID3,ap3);
        ((HealthCardReaderTestDoble) dispensingTerminal.hc).pacients.put(cardID1,ap1);
        ((HealthCardReaderTestDoble) dispensingTerminal.hc).pacients.put(cardID2,ap2);
        ((HealthCardReaderTestDoble) dispensingTerminal.hc).pacients.put(cardID3,ap3);

        Date date1 = new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime();
        Date date2 = new GregorianCalendar(2040, Calendar.FEBRUARY, 11).getTime();
        Byte nOrder = 1;
        Set<ProductID> medicines = new HashSet<>();
        medicines.add(prodID1);
        medicines.add(prodID2);
        medicines.add(prodID3);
        medicines.add(prodID4);
        medicines.add(prodID5);
        Dispensing dispensing = new Dispensing(nOrder,date1,date2,medicines);
        ((NationalHealthServiceTestDoble) dispensingTerminal.sns).dispensacions.put(cardID1,dispensing);
    }

    @Test
    void getePrescription() throws NotValidePrescriptionException, HealthCardException, ConnectException {
        PatientContr ap1 = new PatientContr(new BigDecimal("3.0"));
        dispensingTerminal.getePrescription('c');
        assertFalse(dispensingTerminal.getDispensing().isCompleted());
        assertEquals(ap1,dispensingTerminal.getPatCont());
    }

    @Test
    void saleProcessTest() throws SaleClosedException, NotValidePrescriptionException, HealthCardException, ConnectException, DispensingNotAvailableException, ProductIDException {
        ProductID prodID1 = new ProductID("prod1234");
        ProductID prodID2 = new ProductID("prod4321");
        ProductID prodID3 = new ProductID("prod1234");
        ProductID prodID4 = new ProductID("prod4326");
        ProductID prodID5 = new ProductID("prod1854");

        dispensingTerminal.getePrescription('c');
        dispensingTerminal.initNewSale();
        assertFalse(dispensingTerminal.getDispensing().isCompleted());
        dispensingTerminal.enterProduct(prodID1);
        dispensingTerminal.enterProduct(prodID2);
        dispensingTerminal.enterProduct(prodID3);
        assertFalse(dispensingTerminal.getDispensing().isCompleted());
        dispensingTerminal.enterProduct(prodID4);
        dispensingTerminal.enterProduct(prodID5);

        assertFalse(dispensingTerminal.getSale().isClosed());
        dispensingTerminal.finalizeSale();

        assertTrue(dispensingTerminal.getDispensing().isCompleted());
        assertTrue(dispensingTerminal.getSale().isClosed());

    }
}