package models.recombination;

import models.individuals.Individual;

import java.util.logging.Logger;

public abstract class Recombiner {
    protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public abstract Individual getFirstChild(Individual firstParent, Individual secondParent);

    public abstract Individual getSecondChild(Individual firstParent, Individual secondParent);

    @Override
    public abstract String toString();
}
