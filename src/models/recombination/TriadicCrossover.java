package models.recombination;

import models.individuals.Individual;
import models.individuals.IndividualFactory;
import models.population.Population;
import utils.RandomUtils;

public class TriadicCrossover extends Recombiner {
    private final Population population;
    private Individual mask;

    public TriadicCrossover(Population population) {
        this.population = population;
    }

    @Override
    public Individual getFirstChild(Individual firstParent, Individual secondParent) {
        mask = population.getIndividual(RandomUtils.get(0, population.getSize()));
        return getChild(firstParent, secondParent, false);
    }

    @Override
    public Individual getSecondChild(Individual firstParent, Individual secondParent) {
        if (mask == null)
            mask = population.getIndividual(RandomUtils.get(0, population.getSize()));
        return getChild(firstParent, secondParent, true);
    }

    private Individual getChild(Individual firstParent, Individual secondParent, boolean invertCondition) {
        final Object[] result = new Object[firstParent.getSize()];
        for (int i = 0; i < firstParent.getSize(); i++) {
            boolean condition = mask.isGenePresent(i);
            if (invertCondition)
                condition = !condition;
            if (condition)
                result[i] = firstParent.getGene(i);
            else
                result[i] = secondParent.getGene(i);
        }
        return new IndividualFactory().create(firstParent.getCoder(), firstParent.getSize(), result);
    }
}
