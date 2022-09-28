package models.population;

import models.functions.Function;
import models.individuals.Individual;
import models.mutation.Mutator;
import models.picking.Picker;
import models.recombination.Recombiner;
import models.selection.Selector;
import utils.CollectionUtils;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Population {
    private final List<Individual> individuals;

    private final Function function;

    private final Picker picker;
    private final Mutator mutator;
    private final Selector selector;
    private Recombiner recombiner;
    private double bestResult;
    private Individual bestIndividual;

    public Population(List<Individual> individuals, Picker picker, Mutator mutator, Function function, Selector selector) {
        this.individuals = individuals;
        this.picker = picker;
        this.mutator = mutator;
        this.function = function;
        this.selector = selector;
    }

    public double getBestResult() {
        return bestResult;
    }

    public void setBestResult(double bestResult) {
        this.bestResult = bestResult;
    }

    public Individual getBestIndividual() {
        return bestIndividual;
    }

    public void setBestIndividual(Individual bestIndividual) {
        this.bestIndividual = bestIndividual;
    }

    public int getSize() {
        return individuals.size();
    }

    public Picker getPicker() {
        return picker;
    }

    public Mutator getMutator() {
        return mutator;
    }

    public Function getFunction() {
        return function;
    }

    public Selector getSelector() {
        return selector;
    }

    public Recombiner getRecombiner() {
        return recombiner;
    }

    public void setRecombiner(Recombiner recombiner) {
        this.recombiner = recombiner;
    }

    public void estimate() {
        double bestResult = Double.MAX_VALUE;
        Individual bestIndividual = null;
        for (Individual individual : individuals) {
            double result = function.calculate(individual.getValues());
            individual.setFitness(result);
            if (result < bestResult) {
                bestResult = result;
                bestIndividual = individual;
            }
        }
        this.bestIndividual = bestIndividual;
        this.bestResult = bestIndividual != null ? bestIndividual.getFitness() : Double.MAX_VALUE;
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public List<Individual> getIndividuals(int... indices) {
        final List<Individual> result = new ArrayList<>(indices.length);
        for (int index : indices)
            result.add(getIndividual(index));
        return result;
    }

    public List<Individual> getIndividualsRange(int indexFrom, int indexTo) {
        return getIndividuals(IntStream.range(indexFrom, indexTo).toArray());
    }

    public void sort() {
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
    }

    public void mutate() {
        for (Individual individual : individuals)
            mutator.mutate(individual);
    }

    public Population pick() {
        return picker.pick(this);
    }

    public Population recombine() {
        final Population picked = pick();
        final int[] indices = RandomUtils.getRandomPermutation(picked.getSize());
        final int halfSize = getSize() / 2;
        final int[][] splitIndices = CollectionUtils.split(indices, halfSize);
        final List<Individual> recombinedIndividuals = new ArrayList<>(this.getSize());
        Individual firstParent, secondParent;
        for (int i = 0; i < halfSize; i++) {
            firstParent = picked.getIndividual(splitIndices[0][i]);
            secondParent = picked.getIndividual(splitIndices[1][i]);
            recombinedIndividuals.add(recombiner.getFirstChild(firstParent, secondParent));
            recombinedIndividuals.add(recombiner.getSecondChild(firstParent, secondParent));
        }
        final Population recombined = new Population(recombinedIndividuals, picker, mutator, function, selector);
        recombined.setRecombiner(recombiner);
        return recombined;
    }

    public Population select() {
        final Population children = recombine();
        children.mutate();
        final Population selected = selector.select(this, children);
        selected.setRecombiner(recombiner);
        return selected;
    }

    public void addIndividuals(Population another) {
        individuals.addAll(another.individuals);
    }

    public Population merge(Population another) {
        final Population merged = new Population(Stream.concat(this.individuals.stream(),
                another.individuals.stream()).collect(Collectors.toList()),
                picker, mutator, function, selector);

        if (another.bestResult < this.bestResult) {
            merged.bestResult = another.bestResult;
            merged.bestIndividual = another.bestIndividual;
        } else {
            merged.bestResult = this.bestResult;
            merged.bestIndividual = this.bestIndividual;
        }
        merged.setRecombiner(recombiner);
        return merged;
    }
}
