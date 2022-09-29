package models.mutation;

import models.individuals.Individual;

import java.util.logging.Logger;

public abstract class Mutator {
    protected final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public abstract void mutate(Individual source);

    @Override
    public abstract String toString();
}
