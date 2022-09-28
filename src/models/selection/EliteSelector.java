package models.selection;

import models.Probability;
import models.individuals.Individual;
import models.picking.Picker;
import models.picking.TournamentPicker;
import models.population.Population;
import utils.RandomUtils;

import java.util.List;

public class EliteSelector extends Selector {
    private Probability elitePart;

    public EliteSelector(Probability elitePart) {
        this.elitePart = elitePart;
    }

    public Probability getElitePart() {
        return elitePart;
    }

    public void setElitePart(Probability elitePart) {
        this.elitePart = elitePart;
    }

    @Override
    public Population select(Population parents, Population children) {
        final Population merged = parents.merge(children);
        if (merged.getBestIndividual() == null)
            merged.estimate();
        merged.sort();
        final int eliteSize = (int) (parents.getSize() * elitePart.getValue());
        final List<Individual> bestIndividuals = merged.getIndividualsRange(0, eliteSize);
        final Population result = new Population(bestIndividuals, parents.getPicker(), parents.getMutator(), parents.getFunction(), parents.getSelector());
        final Picker picker = new TournamentPicker(2);
        final List<Individual> pickedIndividuals = picker.pick(merged).getIndividuals(RandomUtils.getDistinct(0, merged.getSize(), parents.getSize() - eliteSize));
        final Population picked = new Population(pickedIndividuals, result.getPicker(), result.getMutator(), result.getFunction(), result.getSelector());
        final Population selected = result.merge(picked);
        return selected;
    }
}
