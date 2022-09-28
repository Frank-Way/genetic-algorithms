package models.coding;

import models.individuals.IndividualType;
import utils.ExceptionUtils;

public class CoderFactory {
    public Coder create(IndividualType individualType, double minValue, double maxValue) {
        switch (individualType) {
            case BINARY:
                return new BinaryCoder(minValue, maxValue);
            case DECIMAL:
                return new DecimalCoder(minValue, maxValue);
            case FLOAT:
                return new FloatCoder(minValue, maxValue);
            default:
                throw ExceptionUtils.unknownEnumItem(individualType);
        }
    }
}
