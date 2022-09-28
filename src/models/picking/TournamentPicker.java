package models.picking;

import models.individuals.Individual;
import models.population.Population;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TournamentPicker extends Picker {
    private int parameter;

    public TournamentPicker(int parameter) {
        this.parameter = parameter;
    }

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public Population pick(Population source) {
        final List<Individual> selected = new ArrayList<>(source.getSize());
        for (int i = 0; i < source.getSize(); i++) {
            final int[] indices = RandomUtils.get(0, source.getSize(), parameter);
            final List<Individual> selectedToTournament = source.getIndividuals(indices);
            final Individual winner = selectedToTournament.stream()
                    .max(Comparator.comparingDouble(Individual::getFitness))
                    .orElse(selectedToTournament.get(0));
            selected.add(winner.copy());
        }
        return new Population(selected, source.getPicker(), source.getMutator(), source.getFunction(), source.getSelector());
    }
}
