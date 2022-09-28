package models;

import models.population.Population;
import options.AlgorithmProperties;
import options.ApplicationProperties;
import options.TaskProperties;
import utils.builders.PopulationBuilder;

public class Trainer {
    public void train(ApplicationProperties applicationProperties,
                      AlgorithmProperties algorithmProperties,
                      TaskProperties taskProperties) {
        Population population = new PopulationBuilder().build(algorithmProperties, taskProperties);
        int epoch = 1;
        do {
            population.estimate();
            population = population.select();
        } while (epoch++ <= algorithmProperties.getEpochsCount());
        population.estimate();
        System.out.println(population.getBestResult());
        System.out.println(population.getBestIndividual());
    }
}
