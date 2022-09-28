package models.individuals;

import models.coding.Coder;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BinaryIndividual extends Individual {
    public BinaryIndividual(Coder coder, int size, double... values) {
        super(coder, size, values);
    }

    public BinaryIndividual(Coder coder, int size, Object... genes) {
        super(coder, size, genes);
    }

    protected BinaryIndividual(double[] values, Object[] genes, Coder coder, int size, double fitness) {
        super(values, genes, coder, size, fitness);
    }

    @Override
    public void setGene(int index) {
        this.genes[index] = true;
    }

    @Override
    public void resetGene(int index) {
        this.genes[index] = false;
    }

    @Override
    public double calculateDistance(Individual individual) {
        return IntStream.range(0, genes.length).filter(i -> !genes[i].equals(individual.genes[i])).count();
    }

    @Override
    public boolean isGenePresent(int index) {
        return (Boolean) genes[index];
    }

    @Override
    protected void mutate(int index) {
        genes[index] = !((Boolean) genes[index]);
    }

    @Override
    public String toString() {
        return "BinaryIndividual{" +
                "size=" + getSize() +
                ", genesCount=" + getGenesCount() +
                ", values=" + Arrays.toString(values) +
                ", genes=" + genesToString() +
                "}";
    }

    private String genesToString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < genes.length; i++) {
            if (i % 4 == 0)
                sb.append(" ");
            sb.append((Boolean) genes[i] ? "1" : "0");
        }
        sb.append("]");
        return sb.toString();
//        return "[" + Arrays.stream(genes).map().collect(Collectors.joining(" ")) + "]";
    }
}
