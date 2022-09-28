package models.individuals;

import models.Probability;
import models.coding.Coder;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DecimalIndividual extends Individual {
    public DecimalIndividual(Coder coder, int size, double... values) {
        super(coder, size, values);
    }

    public DecimalIndividual(Coder coder, int size, Object... genes) {
        super(coder, size, genes);
    }

    protected DecimalIndividual(double[] values, Object[] genes, Coder coder, int size, double fitness) {
        super(values, genes, coder, size, fitness);
    }

    @Override
    public void setGene(int index) {
        genes[index] = 1;
    }

    @Override
    public void resetGene(int index) {
        genes[index] = 0;
    }

    @Override
    public double calculateDistance(Individual individual) {
        return IntStream.range(0, this.getSize())
                .mapToDouble(i -> Math.pow(((Integer) this.getGene(i)) - ((Integer) individual.getGene(i)), 2))
                .sum();
    }

    @Override
    public boolean isGenePresent(int index) {
        return ((Integer) genes[index]) != 0;
    }

    @Override
    protected void mutate(int index) {
        final Integer currentValue = (Integer) genes[index];
        final int mutationBias = ((int) Math.ceil(currentValue * 0.01));
        genes[index] = new Probability(0.5).isFired() ?
                currentValue + mutationBias :
                currentValue - mutationBias;
    }

    @Override
    public String toString() {
        return "DecimalIndividual{" +
                "size=" + getSize() +
                ", genes=" + Arrays.toString(genes) +
                "}";
    }
}
