package models.individuals;

import models.Probability;
import models.coding.Coder;
import utils.ExceptionUtils;
import utils.RandomUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class Individual {
    protected final double[] values;

    protected final Object[] genes;

    protected final Coder coder;

    protected final int size;

    protected double fitness;

    public Individual(Coder coder, int size, double... values) {
        this.values = values;
        this.coder = coder;
        this.size = size;
        this.genes = coder.code(size, values);
    }

    public Individual(Coder coder, int size, Object... genes) {
        this.genes = genes;
        this.coder = coder;
        this.size = size;
        this.values = coder.decode(size, genes);
    }

    protected Individual(double[] values, Object[] genes, Coder coder, int size, double fitness) {
        this.values = values;
        this.genes = genes;
        this.coder = coder;
        this.size = size;
        this.fitness = fitness;
    }

    public static Individual combine(Coder coder, Individual... individuals) {
        if (individuals.length < 1)
            throw ExceptionUtils.emptyCollection();

        final int[] sizes = Arrays.stream(individuals).mapToInt(Individual::getGenesCount).toArray();
        final int[] positions = IntStream.range(0, sizes.length)
                .map(i -> Arrays.stream(sizes).limit(i + 1).sum() - sizes[i]).toArray();
        final int size = Arrays.stream(sizes).reduce(Integer::sum).orElse(0);
        final Object[] result = new Object[size];
        for (int i = 0; i < individuals.length; i++)
            System.arraycopy(individuals[i].genes, 0, result, positions[i], sizes[i]);

        return new IndividualFactory().create(coder, individuals[0].size, result);
    }

    public int getSize() {
        return size;
    }

    public int getGenesCount() {
        return genes.length;
    }

    protected Object[] getGenes() {
        return genes;
    }

    public int getValuesCount() {
        return values.length;
    }

    public double[] getValues() {
        return values;
    }

    public double getValue(int index) {
        return values[index];
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Object getGene(int index) {
        return genes[index];
    }

    public abstract void setGene(int index);

    public abstract void resetGene(int index);

    public abstract boolean isGenePresent(int index);

    public void setGenes(int indexFrom, int indexTo) {
        for (int index = indexFrom; index < indexTo; index++)
            setGene(index);
    }

    public void resetGenes(int indexFrom, int indexTo) {
        for (int index = indexFrom; index < indexTo; index++)
            resetGene(index);
    }

    protected void setGene(int index, Object gene) {
        genes[index] = gene;
    }

    public void setGenes(int indexFrom, int indexTo, Object gene) {
        for (int index = indexFrom; index < indexTo; index++)
            setGene(index, gene);
    }

    public abstract double calculateDistance(Individual individual);

    protected abstract void mutate(int index);

    protected void mutate() {
        mutate(RandomUtils.get(0, genes.length));
    }

    public void mutate(int index, Probability probability) {
        if (probability.isFired())
            mutate(index);
    }

    public void mutate(Probability probability) {
        if (probability.isFired())
            mutate();
    }

    public void mutate(Probability individualMutateProbability, Probability geneMutateProbability) {
        if (individualMutateProbability.isFired())
            for (int gene = 0; gene < genes.length; gene++)
                mutate(gene, geneMutateProbability);
    }

    public Individual getSlice(int indexFrom) {
        return getSlice(indexFrom, this.getGenesCount());
    }

    public Individual getSlice(int indexFrom, int indexTo) {
        return new IndividualFactory().create(coder, size, Arrays.copyOfRange(this.genes, indexFrom, indexTo));
    }

    public Individual copyGenes(Individual individual, int thisIndexFrom, int anotherIndexFrom, int count) {
        return combine(coder, this.getSlice(0, thisIndexFrom),
                individual.getSlice(anotherIndexFrom, anotherIndexFrom + count),
                this.getSlice(thisIndexFrom + count));
    }

    public Individual extend(Individual individual) {
        if (!this.getClass().equals(individual.getClass()))
            throw ExceptionUtils.incompatibleTypes(this.getClass(), individual.getClass());

        return combine(coder, this, individual);
    }

    public Individual[] split(int... indices) {
        if (indices.length < 1)
            return new Individual[]{this};

        final Individual[] result = new Individual[indices.length + 1];

        int indexFrom = 0;
        for (int i = 0; i < indices.length; i++) {
            int indexTo = indices[i];
            result[i] = this.getSlice(indexFrom, indexTo);
            indexFrom = indexTo;
        }
        result[indices.length - 1] = this.getSlice(indexFrom, this.getSize());

        return result;
    }

    @Override
    public abstract String toString();

    public Coder getCoder() {
        return coder;
    }

    public Individual copy() {
        return new IndividualFactory().create(this);
    }
}
