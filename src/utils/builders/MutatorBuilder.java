package utils.builders;

import models.mutation.ManyGenesMutator;
import models.mutation.Mutator;
import models.mutation.OneGeneMutator;
import options.AlgorithmProperties;
import utils.ExceptionUtils;

public class MutatorBuilder {
    public Mutator build(AlgorithmProperties algorithmProperties) {
        switch (algorithmProperties.getMutationType()) {
            case ONE_GENE:
                return new OneGeneMutator(algorithmProperties.getMutationIndividualProbability());
            case MANY_GENES:
                return new ManyGenesMutator(algorithmProperties.getMutationIndividualProbability(),
                        algorithmProperties.getMutationGeneProbability());
            default:
                throw ExceptionUtils.unknownEnumItem(algorithmProperties.getMutationType());
        }
    }
}
