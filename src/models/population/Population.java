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
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Population {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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

    @Override
    public String toString() {
        return "Population{" +
//                "individuals=" + individuals +
                "function=" + function +
                ", picker=" + picker +
                ", mutator=" + mutator +
                ", selector=" + selector +
                ", recombiner=" + recombiner +
                ", bestResult=" + bestResult +
                ", bestIndividual=" + bestIndividual +
                '}';
    }

    public String individualsToString() {
        return "individuals=" + individuals;
    }

    public void estimate() {
        logger.finer("Начало оценки популяции");
        double bestResult = Double.MAX_VALUE;
        Individual bestIndividual = null;
        for (Individual individual : individuals) {
            double result = function.calculate(individual.getValues());
            logger.finest("Оценка для особи: " + individual + "\n" + result);
            individual.setFitness(result);
            if (result < bestResult) {
                bestResult = result;
                bestIndividual = individual;
            }
        }
        this.bestIndividual = bestIndividual;
        this.bestResult = bestIndividual != null ? bestIndividual.getFitness() : Double.MAX_VALUE;
        logger.finer("Наилучший результат после оценки всех особей: " + this.bestResult);
        logger.finer("Завершение оценки популяции");
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
        logger.finer("Начало сортировки популяции по пригодности");
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
        logger.finer("Завершение сортировки популяции по пригодности");
    }

    public void mutate() {
        logger.finer("Начало мутации");
        for (Individual individual : individuals) {
            logger.finest("Мутация особи: " + individual);
            mutator.mutate(individual);
            logger.finest("Мутировавшая особь: " + individual);
        }
        logger.finer("Завершение мутации");
    }

    public Population pick() {
        logger.finer("Начало отбора особей для рекомбинации");
        final Population picked = picker.pick(this);
        logger.finer("Завершение отбора особей для рекомбинации");
        return picked;
    }

    public Population recombine() {
        logger.finer("Отбор особей для рекомбинации");
        final Population picked = pick();
        logger.finer("Популяция отобранных особей: " + picked);
        logger.finest("Отобранные особи: " + picked.individualsToString());

        final int[] indices = RandomUtils.getRandomPermutation(picked.getSize());
        final int halfSize = getSize() / 2;
        final int[][] splitIndices = CollectionUtils.split(indices, halfSize);
        final List<Individual> recombinedIndividuals = new ArrayList<>(this.getSize());
        Individual firstParent, secondParent;
        Individual firstChild, secondChild;
        logger.finer("Начало рекомбинации");
        for (int i = 0; i < halfSize; i++) {
            firstParent = picked.getIndividual(splitIndices[0][i]);
            secondParent = picked.getIndividual(splitIndices[1][i]);
            logger.finest((i + 1) + " рекомбинация.");
            logger.finest("Родитель 1: " + firstParent);
            logger.finest("Родитель 2: " + secondParent);
            firstChild = recombiner.getFirstChild(firstParent, secondParent);
            secondChild = recombiner.getSecondChild(firstParent, secondParent);
            logger.finest("Потомок 1: " + firstChild);
            logger.finest("Потомок 2: " + secondChild);
            recombinedIndividuals.add(firstChild);
            recombinedIndividuals.add(secondChild);
        }
        logger.finer("Завершение рекомбинации");
        final Population recombined = new Population(recombinedIndividuals, picker, mutator, function, selector);
        recombined.setRecombiner(recombiner);
        return recombined;
    }

    public Population select() {
        logger.finer("Выполнение рекомбинации");
        final Population children = recombine();
        logger.finer("Популяция потомков: " + children);
        logger.finest("Потомки: " + children.individualsToString());

        logger.finer("Мутация потомков");
        children.mutate();
        logger.finer("Популяция потомков: " + children);
        logger.finest("Потомки: " + children.individualsToString());

        logger.finer("Формирование новой популяции");
        final Population selected = selector.select(this, children);
        selected.setRecombiner(recombiner);
        logger.finer("Новоя популяция: " + selected);
        logger.finest("Особи: " + selected.individualsToString());

        return selected;
    }

    public void addIndividuals(Population another) {
        individuals.addAll(another.individuals);
    }

    public Population merge(Population another) {
        logger.finer("Начало слияния популяций");
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
        logger.finer("Завершение слияния популяций");
        return merged;
    }
}
