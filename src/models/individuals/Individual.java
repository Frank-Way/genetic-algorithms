package models.individuals;

import utils.ExceptionUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class Individual {
    protected final Object[] genes;

    protected Individual(Object[] genes) {
        this.genes = genes;
    }

    public int size() {
        return genes.length;
    }

    public Object getGene(int index) {
        return genes[index];
    }

    public abstract void setGene(int index);

    public abstract void resetGene(int index);

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

    public Individual getSlice(int indexFrom) {
        return getSlice(indexFrom, this.size());
    }

    public Individual getSlice(int indexFrom, int indexTo) {
        return new IndividualFactory().createIndividual(Arrays.copyOfRange(this.genes, indexFrom, indexTo));
    }

    public Individual copyGenes(Individual individual, int thisIndexFrom, int anotherIndexFrom, int count) {
        return combine(this.getSlice(0, thisIndexFrom),
                individual.getSlice(anotherIndexFrom, anotherIndexFrom + count),
                this.getSlice(thisIndexFrom + count));
    }

    public Individual extend(Individual individual) {
        if (!this.getClass().equals(individual.getClass()))
            throw ExceptionUtils.newIncompatibleTypes(this.getClass(), individual.getClass());

        return combine(this, individual);
    }

    public Individual[] split(int ... indices) {
        if (indices.length < 1)
            return new Individual[] {this};

        final Individual[] result = new Individual[indices.length + 1];

        int indexFrom = 0;
        for (int i = 0; i < indices.length; i++) {
            int indexTo = indices[i];
            result[i] = this.getSlice(indexFrom, indexTo);
            indexFrom = indexTo;
        }
        result[indices.length - 1] = this.getSlice(indexFrom, this.size());

        return result;
    }

    public static Individual combine(Individual ... individuals) {
        if (individuals.length < 1)
            throw ExceptionUtils.newEmptyCollection();

        final int[] sizes = Arrays.stream(individuals).mapToInt(Individual::size).toArray();
        final int[] positions = IntStream.range(0, sizes.length)
                .map(i -> Arrays.stream(sizes).limit(i + 1).sum() - sizes[i]).toArray();
        final int size = Arrays.stream(sizes).reduce(Integer::sum).orElse(0);
        final Object[] result = new Object[size];
        for (int i = 0; i < individuals.length; i++)
            System.arraycopy(individuals[i].genes, 0, result, positions[i], sizes[i]);

        return new IndividualFactory().createIndividual(result);
    }
}
