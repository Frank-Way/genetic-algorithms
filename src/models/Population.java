package models;

import models.individuals.Individual;

import java.util.Collection;

public class Population {
    private final Collection<Individual> individuals;

    public Population(Collection<Individual> individuals) {
        this.individuals = individuals;
    }
}
