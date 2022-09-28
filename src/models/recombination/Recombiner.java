package models.recombination;

import models.individuals.Individual;

public abstract class Recombiner {
    public abstract Individual getFirstChild(Individual firstParent, Individual secondParent);

    public abstract Individual getSecondChild(Individual firstParent, Individual secondParent);
}
