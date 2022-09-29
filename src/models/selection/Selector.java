package models.selection;

import models.population.Population;

import java.util.logging.Logger;

public abstract class Selector {
    protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public abstract Population select(Population parents, Population children);

    @Override
    public abstract String toString();
}
