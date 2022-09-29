package models.recombination;

import models.individuals.Individual;
import utils.CollectionUtils;
import utils.RandomUtils;
import utils.Utils;

import java.util.Arrays;

public class ManyPointsCrossover extends Recombiner {
    private int[] pointsToCut;
    private int pointsCount;

    public ManyPointsCrossover(int pointsCount) {
        this.pointsCount = pointsCount;
    }

    public void setPointsCount(int pointsCount) {
        this.pointsCount = pointsCount;
    }

    @Override
    public String toString() {
        return "ManyPointsCrossover{" +
                "pointsToCut=" + Arrays.toString(pointsToCut) +
                ", pointsCount=" + pointsCount +
                '}';
    }

    @Override
    public Individual getFirstChild(Individual firstParent, Individual secondParent) {
        logger.finer("Получение первого потомка многоточечным кроссинговером");
        randomInitPointsToCut(firstParent.getGenesCount());
        logger.finest("Точки разреза: " + Arrays.toString(pointsToCut));
        return getChild(firstParent, secondParent, false);
    }

    @Override
    public Individual getSecondChild(Individual firstParent, Individual secondParent) {
        logger.finer("Получение второго потомка многоточечным кроссинговером");
        if (pointsToCut == null)
            randomInitPointsToCut(firstParent.getGenesCount());
        logger.finest("Точки разреза: " + Arrays.toString(pointsToCut));
        return getChild(firstParent, secondParent, true);
    }

    private Individual getChild(Individual firstParent, Individual secondParent, boolean invertCondition) {
        Individual result = null;
        for (int i = 1; i < pointsToCut.length; i++) {
            boolean condition = Utils.isEven(i);
            if (invertCondition)
                condition = !condition;
            Individual part = condition ?
                    firstParent.getSlice(pointsToCut[i - 1], pointsToCut[i]) :
                    secondParent.getSlice(pointsToCut[i - 1], pointsToCut[i]);
            if (result == null)
                result = part;
            else
                result = result.extend(part);
        }
        return result;
    }

    private void randomInitPointsToCut(int maxValue) {
        final int[] tmpArray = CollectionUtils.sort(RandomUtils.getDistinct(1, maxValue - 1, pointsCount));
        pointsToCut = new int[tmpArray.length + 2];
        System.arraycopy(tmpArray, 0, pointsToCut, 1, pointsCount);
        pointsToCut[0] = 0;
        pointsToCut[pointsCount] = maxValue;
    }
}
