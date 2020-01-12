package services;

import data.HealthCardID;
import exceptions.HealthCardException;

public interface HealthCardReader {

    HealthCardID getHealthCardID() throws HealthCardException;
}
