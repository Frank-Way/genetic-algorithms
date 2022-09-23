package models.individuals;

import utils.CollectionUtils;
import utils.ExceptionUtils;

public class IndividualFactory {
    public Individual createIndividual(Object[] genes) {
        if (genes.length < 1)
            throw ExceptionUtils.newEmptyCollection();

        Class<?> itemsType = genes[0].getClass();
        if (!CollectionUtils.contains(itemsType, getAllowedTypes()))
            throw ExceptionUtils.newUnknownType(itemsType);

        else if (itemsType.equals(Boolean.class))
            return new BinaryIndividual((Boolean[]) genes);

        else throw ExceptionUtils.newUnknownType(itemsType);
    }

    private static Class<?>[] getAllowedTypes() {
        return new Class[] {Integer.class, Double.class, Boolean.class};
    }
}
