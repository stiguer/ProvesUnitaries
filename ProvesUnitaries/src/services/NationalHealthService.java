package services; // Package for involved services

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;
import exceptions.HealthCardException;
import exceptions.NotValidePrescriptionException;
import exceptions.ProductIDException;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;

import java.net.ConnectException;
import java.util.List;

/**
 * External service for managing and storing ePrescriptions from population
 */

public interface NationalHealthService {
    Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException;
    PatientContr getPatientContr(HealthCardID hcID) throws ConnectException;
    ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException;
    List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException;
}
