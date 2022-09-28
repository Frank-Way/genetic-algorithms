package models.picking;

import models.population.Population;

public abstract class Picker {
    public abstract Population pick(Population source);
}
