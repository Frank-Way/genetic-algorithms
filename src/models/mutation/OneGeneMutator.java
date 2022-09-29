package models.mutation;

import models.Probability;
import models.individuals.Individual;

public class OneGeneMutator extends Mutator {
    protected Probability individualMutationProbability;

    public OneGeneMutator(Probability individualMutationProbability) {
        this.individualMutationProbability = individualMutationProbability;
    }

    public Probability getIndividualMutationProbability() {
        return individualMutationProbability;
    }

    public void setIndividualMutationProbability(Probability individualMutationProbability) {
        this.individualMutationProbability = individualMutationProbability;
    }

    @Override
    public String toString() {
        return "OneGeneMutator{" +
                "individualMutationProbability=" + individualMutationProbability +
                '}';
    }

    @Override
    public void mutate(Individual source) {
        logger.finer("Выполнение мутации одного гена");
        source.mutate(individualMutationProbability);
    }
}
