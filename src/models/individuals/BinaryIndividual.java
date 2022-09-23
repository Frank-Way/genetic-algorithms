package models.individuals;

public class BinaryIndividual extends Individual{
    public BinaryIndividual(Boolean[] genes) {
        super(genes);
    }

    @Override
    public void setGene(int index) {
        this.genes[index] = true;
    }

    @Override
    public void resetGene(int index) {
        this.genes[index] = false;
    }
}
