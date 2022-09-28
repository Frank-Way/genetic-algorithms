package models.individuals;

import models.coding.BinaryCoder;
import models.coding.Coder;
import models.coding.DecimalCoder;
import models.coding.FloatCoder;
import utils.ExceptionUtils;

import java.util.Arrays;

public class IndividualFactory {
    public static IndividualType mapCoderToIndividualType(Coder coder) {
        if (coder instanceof BinaryCoder)
            return IndividualType.BINARY;
        else if (coder instanceof DecimalCoder)
            return IndividualType.DECIMAL;
        else if (coder instanceof FloatCoder)
            return IndividualType.FLOAT;
        else
            throw ExceptionUtils.unknownType(coder.getClass(), "Не известный тип кодера");
    }

    private static Class<?>[] getAllowedTypes() {
        return new Class[]{Integer.class, Double.class, Boolean.class};
    }

    public Individual create(IndividualType individualType, Coder coder, int size, double... values) {
        if (size < 1)
            throw ExceptionUtils.valueLessThanThreshold(size, 1);

        switch (individualType) {
            case BINARY:
                return new BinaryIndividual(coder, size, values);
            case DECIMAL:
                return new DecimalIndividual(coder, size, values);
            case FLOAT:
                return new FloatIndividual(coder, size, values);
            default:
                throw ExceptionUtils.unknownEnumItem(individualType);
        }
    }

    public Individual create(Coder coder, int size, double... values) {
        if (values.length < 1)
            throw ExceptionUtils.emptyCollection();

        final IndividualType individualType = mapCoderToIndividualType(coder);

        return create(individualType, coder, size, values);
    }

    public Individual create(IndividualType individualType, Coder coder, int size, Object... genes) {
        if (size < 1)
            throw ExceptionUtils.valueLessThanThreshold(size, 1);

        switch (individualType) {
            case BINARY:
                return new BinaryIndividual(coder, size, genes);
            case DECIMAL:
                return new DecimalIndividual(coder, size, genes);
            case FLOAT:
                return new FloatIndividual(coder, size, genes);
            default:
                throw ExceptionUtils.unknownEnumItem(individualType);
        }
    }

    public Individual create(Coder coder, int size, Object... values) {
        if (values.length < 1)
            throw ExceptionUtils.emptyCollection();

        final IndividualType individualType = mapCoderToIndividualType(coder);

        return create(individualType, coder, size, values);
    }

    public Individual create(Individual individual) {
        final IndividualType individualType = mapCoderToIndividualType(individual.getCoder());
        final double[] valuesCopy = Arrays.copyOf(individual.getValues(), individual.getValuesCount());
        final Object[] genesCopy = Arrays.copyOf(individual.getGenes(), individual.getGenesCount());
        switch (individualType) {
            case BINARY:
                return new BinaryIndividual(valuesCopy, genesCopy, individual.getCoder(), individual.getSize(), individual.getFitness());
            case DECIMAL:
                return new DecimalIndividual(valuesCopy, genesCopy, individual.getCoder(), individual.getSize(), individual.getFitness());
            case FLOAT:
                return new FloatIndividual(valuesCopy, genesCopy, individual.getCoder(), individual.getSize(), individual.getFitness());
            default:
                throw ExceptionUtils.unknownEnumItem(individualType);
        }
    }
}
