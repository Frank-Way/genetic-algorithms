package utils.builders;

import models.coding.Coder;
import models.functions.Function;
import models.functions.FunctionFactory;
import models.functions.InvertedFunction;
import models.mutation.Mutator;
import models.picking.Picker;
import models.population.Population;
import models.recombination.Recombiner;
import models.selection.Selector;
import options.AlgorithmProperties;
import options.TaskProperties;

public class PopulationBuilder {
    public Population build(AlgorithmProperties algorithmProperties, TaskProperties taskProperties) {
        final Picker picker = new PickerBuilder().build(algorithmProperties);
        final Selector selector = new SelectorBuilder().build(algorithmProperties);
        final Mutator mutator = new MutatorBuilder().build(algorithmProperties);
        final Coder coder = new CoderBuilder().build(algorithmProperties, taskProperties);
        Function function;
        final FunctionFactory functionFactory = new FunctionFactory();
        switch (taskProperties.getFunctionInputsCount()) {
            case 1:
                function = functionFactory.createOneArg(taskProperties.getFunctionName());
                break;
            case 2:
                function = functionFactory.createTwoArgs(taskProperties.getFunctionName());
                break;
            default:
                function = functionFactory.create(taskProperties.getFunctionName());
                break;
        }
        if (taskProperties.isFunctionInverted())
            function = new InvertedFunction(function);
        final Population population = new models.population.PopulationBuilder()
                .populationSize(algorithmProperties.getPopulationSize())
                .individualSize(algorithmProperties.getIndividualSize())
                .individualType(algorithmProperties.getIndividualType())
                .function(function)
                .valuesCount(taskProperties.getFunctionInputsCount())
                .maxValue(taskProperties.getDataValueMax())
                .minValue(taskProperties.getDataValueMin())
                .coder(coder)
                .picker(picker)
                .selector(selector)
                .mutator(mutator)
                .build();

        final Recombiner recombiner = new RecombinerBuilder().build(algorithmProperties, population);
        population.setRecombiner(recombiner);
        return population;
    }
}
