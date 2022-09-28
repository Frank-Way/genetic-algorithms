package models.mutation;

import models.Probability;
import models.individuals.Individual;

public class ManyGenesMutator extends OneGeneMutator {
    private Probability geneMutationProbability;

    public ManyGenesMutator(Probability individualMutationProbability, Probability geneMutationProbability) {
        super(individualMutationProbability);
        this.geneMutationProbability = geneMutationProbability;
    }

    public Probability getGeneMutationProbability() {
        return geneMutationProbability;
    }

    public void setGeneMutationProbability(Probability geneMutationProbability) {
        this.geneMutationProbability = geneMutationProbability;
    }

    @Override
    public void mutate(Individual source) {
        source.mutate(individualMutationProbability, geneMutationProbability);
    }
}
