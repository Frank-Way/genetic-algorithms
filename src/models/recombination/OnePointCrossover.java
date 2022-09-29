package models.recombination;

import models.individuals.Individual;
import utils.RandomUtils;

public class OnePointCrossover extends Recombiner {
    private Integer pointToCut;

    @Override
    public String toString() {
        return "OnePointCrossover{" +
                "pointToCut=" + pointToCut +
                '}';
    }

    @Override
    public Individual getFirstChild(Individual firstParent, Individual secondParent) {
        logger.finer("Получение первого потомка одноточечным кроссинговером");
        pointToCut = RandomUtils.getBoxed(1, firstParent.getGenesCount() - 1);
        logger.finest("Точка разрыва: " + pointToCut);
        return firstParent.getSlice(0, pointToCut).extend(secondParent.getSlice(pointToCut));
    }

    @Override
    public Individual getSecondChild(Individual firstParent, Individual secondParent) {
        logger.finer("Получение второго потомка одноточечным кроссинговером");
        if (pointToCut == null)
            pointToCut = RandomUtils.getBoxed(1, firstParent.getGenesCount() - 1);
        logger.finest("Точка разрыва: " + pointToCut);
        return secondParent.getSlice(0, pointToCut).extend(firstParent.getSlice(pointToCut));
    }
}
