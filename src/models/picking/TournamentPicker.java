package models.picking;

import models.individuals.Individual;
import models.population.Population;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public String toString() {
        return "TournamentPicker{" +
                "parameter=" + parameter +
                '}';
    }

    @Override
    public Population pick(Population source) {
        logger.finer("Выполнение турнирного отбора");
        final List<Individual> selected = new ArrayList<>(source.getSize());
        for (int i = 0; i < source.getSize(); i++) {
            final int[] indices = RandomUtils.get(0, source.getSize(), parameter);
            final List<Individual> selectedToTournament = source.getIndividuals(indices);
            logger.finest((i + 1) + " этап турнира. Отобраны особи: " +
                    selectedToTournament.stream().map(Individual::toString)
                            .collect(Collectors.joining(", ")));
            final Individual winner = selectedToTournament.stream()
                    .max(Comparator.comparingDouble(Individual::getFitness))
                    .orElse(selectedToTournament.get(0));
            logger.finest("Победитель: " + winner);
            selected.add(winner.copy());
        }
        logger.finer("Завершение турнирного отбора");
        return new Population(selected, source.getPicker(), source.getMutator(), source.getFunction(), source.getSelector());
    }
}
