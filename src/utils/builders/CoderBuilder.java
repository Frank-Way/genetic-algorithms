package utils.builders;

import models.coding.BinaryCoder;
import models.coding.Coder;
import models.coding.DecimalCoder;
import models.coding.FloatCoder;
import options.AlgorithmProperties;
import options.TaskProperties;
import utils.ExceptionUtils;

public class CoderBuilder {
    public Coder build(AlgorithmProperties algorithmProperties, TaskProperties taskProperties) {
        switch (algorithmProperties.getIndividualType()) {
            case BINARY:
                return new BinaryCoder(taskProperties.getDataValueMin(), taskProperties.getDataValueMax());
            case DECIMAL:
                return new DecimalCoder(taskProperties.getDataValueMin(), taskProperties.getDataValueMax());
            case FLOAT:
                return new FloatCoder(taskProperties.getDataValueMin(), taskProperties.getDataValueMax());
            default:
                throw ExceptionUtils.unknownEnumItem(algorithmProperties.getIndividualType());
        }
    }
}
