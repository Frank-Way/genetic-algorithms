package models.recombination;

import models.individuals.Individual;
import utils.RandomUtils;

public class OnePointCrossover extends Recombiner {
    private Integer pointToCut;

    @Override
    public Individual getFirstChild(Individual firstParent, Individual secondParent) {
        pointToCut = RandomUtils.getBoxed(1, firstParent.getGenesCount() - 1);
        return firstParent.getSlice(0, pointToCut).extend(secondParent.getSlice(pointToCut));
    }

    @Override
    public Individual getSecondChild(Individual firstParent, Individual secondParent) {
        if (pointToCut == null)
            pointToCut = RandomUtils.getBoxed(1, firstParent.getGenesCount() - 1);
        return secondParent.getSlice(0, pointToCut).extend(firstParent.getSlice(pointToCut));
    }
}
