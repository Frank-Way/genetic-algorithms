package models.mutation;

import models.individuals.Individual;

public abstract class Mutator {
    public abstract void mutate(Individual source);
}
