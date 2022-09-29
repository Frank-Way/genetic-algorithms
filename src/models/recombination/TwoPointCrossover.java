package models.recombination;

import models.individuals.Individual;
import utils.CollectionUtils;
import utils.RandomUtils;

public class TwoPointCrossover extends Recombiner {
    private Integer pointToCut1;
    private Integer pointToCut2;

    @Override
    public String toString() {
        return "TwoPointCrossover{" +
                "pointToCut1=" + pointToCut1 +
                ", pointToCut2=" + pointToCut2 +
                '}';
    }

    @Override
    public Individual getFirstChild(Individual firstParent, Individual secondParent) {
        logger.finer("Получение первого потомка двухточечным кроссинговером");
        randomInitPointsToCut(firstParent.getGenesCount());
        logger.finest("Точки для разреза: " + pointToCut1 + ", " + pointToCut2);
        return firstParent.getSlice(0, pointToCut1)
                .extend(secondParent.getSlice(pointToCut1, pointToCut2))
                .extend(firstParent.getSlice(pointToCut2));
    }

    @Override
    public Individual getSecondChild(Individual firstParent, Individual secondParent) {
        logger.finer("Получение второго потомка двухточечным кроссинговером");
        if (pointToCut1 == null || pointToCut2 == null)
            randomInitPointsToCut(firstParent.getGenesCount());
        logger.finest("Точки для разреза: " + pointToCut1 + ", " + pointToCut2);
        return secondParent.getSlice(0, pointToCut1)
                .extend(firstParent.getSlice(pointToCut1, pointToCut2))
                .extend(secondParent.getSlice(pointToCut2));
    }

    private void randomInitPointsToCut(int maxValue) {
        int[] points = CollectionUtils.sort(RandomUtils.getDistinct(1, maxValue, 2));
        pointToCut1 = points[0];
        pointToCut2 = points[1];
    }
}
