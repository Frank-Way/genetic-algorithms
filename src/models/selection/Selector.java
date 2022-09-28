package models.selection;

import models.population.Population;

public abstract class Selector {
    public abstract Population select(Population parents, Population children);
}
