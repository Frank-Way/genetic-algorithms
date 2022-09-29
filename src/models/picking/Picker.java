package models.picking;

import models.population.Population;

import java.util.logging.Logger;

public abstract class Picker {
    protected final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public abstract Population pick(Population source);

    @Override
    public abstract String toString();
}
