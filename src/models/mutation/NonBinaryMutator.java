package models.mutation;

import models.Probability;

public abstract class NonBinaryMutator extends Mutator {
    private Probability mutationPower;

    public NonBinaryMutator(Probability mutationPower) {
        this.mutationPower = mutationPower;
    }

    public Probability getMutationPower() {
        return mutationPower;
    }

    public void setMutationPower(Probability mutationPower) {
        this.mutationPower = mutationPower;
    }
}
