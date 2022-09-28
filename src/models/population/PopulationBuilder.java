package models.population;

import models.coding.Coder;
import models.coding.CoderFactory;
import models.functions.Function;
import models.individuals.Individual;
import models.individuals.IndividualFactory;
import models.individuals.IndividualType;
import models.mutation.Mutator;
import models.picking.Picker;
import models.recombination.Recombiner;
import models.selection.Selector;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class PopulationBuilder {
    private int populationSize;
    private double minValue;
    private double maxValue;
    private int individualSize;
    private int valuesCount;

    private Function function;

    private Picker picker;

    private Mutator mutator;

    private Selector selector;

    private Coder coder;

    private IndividualType individualType;
    private Recombiner recombiner;
    private double bestResult;
    private Individual bestIndividual;

    public PopulationBuilder() {
    }

    public PopulationBuilder populationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public PopulationBuilder minValue(double minValue) {
        this.minValue = minValue;
        return this;
    }

    public PopulationBuilder maxValue(double maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public PopulationBuilder individualSize(int individualSize) {
        this.individualSize = individualSize;
        return this;
    }

    public PopulationBuilder valuesCount(int valuesCount) {
        this.valuesCount = valuesCount;
        return this;
    }

    public PopulationBuilder function(Function function) {
        this.function = function;
        return this;
    }

    public PopulationBuilder picker(Picker picker) {
        this.picker = picker;
        return this;
    }

    public PopulationBuilder mutator(Mutator mutator) {
        this.mutator = mutator;
        return this;
    }

    public PopulationBuilder selector(Selector selector) {
        this.selector = selector;
        return this;
    }

    public PopulationBuilder coder(Coder coder) {
        this.coder = coder;
        return this;
    }

    public PopulationBuilder individualType(IndividualType individualType) {
        this.individualType = individualType;
        return this;
    }

    public PopulationBuilder recombiner(Recombiner recombiner) {
        this.recombiner = recombiner;
        return this;
    }

    public PopulationBuilder bestResult(double bestResult) {
        this.bestResult = bestResult;
        return this;
    }

    public PopulationBuilder bestIndividual(Individual bestIndividual) {
        this.bestIndividual = bestIndividual;
        return this;
    }

    public Population build() {
        individualType = individualType == null ? IndividualFactory.mapCoderToIndividualType(coder) : individualType;
        coder = coder == null ? new CoderFactory().create(individualType, minValue, maxValue) : coder;

        final List<Individual> individuals = new ArrayList<>(populationSize);
        final IndividualFactory individualFactory = new IndividualFactory();

        for (int i = 0; i < populationSize; i++)
            individuals.add(individualFactory.create(individualType, coder, individualSize,
                    RandomUtils.get(minValue, maxValue, valuesCount)));

        final Population result = new Population(individuals, picker, mutator, function, selector);

        result.setRecombiner(recombiner);
        result.setBestResult(bestResult);
        result.setBestIndividual(bestIndividual);

        return result;
    }
}
