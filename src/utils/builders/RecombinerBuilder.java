package utils.builders;

import models.population.Population;
import models.recombination.*;
import options.AlgorithmProperties;
import utils.ExceptionUtils;

public class RecombinerBuilder {
    public Recombiner build(AlgorithmProperties algorithmProperties, Population initial) {
        switch (algorithmProperties.getRecombinerType()) {
            case ONE_POINT_CROSSOVER:
                return new OnePointCrossover();
            case TWO_POINT_CROSSOVER:
                return new TwoPointCrossover();
            case MANY_POINTS_CROSSOVER:
                return new ManyPointsCrossover(algorithmProperties.getRecombinerManyCount());
            case TRIADIC_CROSSOVER:
                return new TriadicCrossover(initial);
            default:
                throw ExceptionUtils.unknownEnumItem(algorithmProperties.getRecombinerType());
        }
    }
}
