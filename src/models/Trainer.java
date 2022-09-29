package models;

import models.population.Population;
import options.AlgorithmProperties;
import options.ApplicationProperties;
import options.TaskProperties;
import utils.builders.PopulationBuilder;

import java.util.logging.Logger;

public class Trainer {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void train(ApplicationProperties applicationProperties,
                      AlgorithmProperties algorithmProperties,
                      TaskProperties taskProperties) {
        logger.fine("Создание начальной популяции");
        Population population = new PopulationBuilder().build(algorithmProperties, taskProperties);
        logger.fine("Популяция успешно создана");
        logger.finer("Популяция: " + population);
        logger.finest("Особи популяции: " + population.individualsToString());
        int epoch = 1;
        logger.fine("Запуск обучения");
        do {
            population.estimate();
            logger.finer("Эпоха " + epoch + ". Результат: " + population.getBestResult() + "\n" +
                    "Выполнение очередной эпохи");
            logger.finer("Популяция: " + population);
            logger.finest("Особи популяции: " + population.individualsToString());
            population = population.select();
        } while (epoch++ <= algorithmProperties.getEpochsCount());
        logger.fine("Завершение обучения");
        logger.finer("Популяция после обучения: " + population);
        logger.finest("Особи популяции: " + population.individualsToString());

        logger.fine("Выполнение оценки популяции после обучения");
        population.estimate();
        logger.info("Результаты оценки популяции:\nНаилучший результат: " + population.getBestResult() + "\n" +
                "Наилучшая особь: " + population.getBestIndividual());
    }
}
