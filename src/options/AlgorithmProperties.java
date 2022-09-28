package options;

import models.Probability;
import models.individuals.IndividualType;
import models.mutation.MutatorType;
import models.picking.PickerType;
import models.recombination.RecombinerType;
import models.selection.SelectorType;

import java.io.IOException;
import java.util.Properties;

public class AlgorithmProperties extends AbstractProperties {
    private final IndividualType individualType;
    private final int individualSize;
    private final int populationSize;
    private final int epochsCount;
    private final PickerType pickingType;
    private final int pickingTournamentParameter;

    private final SelectorType selectionType;

    private final Probability selectionElitePart;
    private final MutatorType mutationType;
    private final Probability mutationIndividualProbability;
    private final Probability mutationGeneProbability;
    private final RecombinerType recombinerType;
    private final int recombinerManyCount;

    public AlgorithmProperties() throws IOException {
        this(propertiesFileNamePrefix + "algorithm.properties");
    }

    public AlgorithmProperties(String propertiesFileName) throws IOException {
        Properties properties = readProperties(propertiesFileName);
        individualType = IndividualType.valueOf(properties.getProperty("individual.type", "BINARY"));
        individualSize = Integer.parseInt(properties.getProperty("individual.size", "10"));
        populationSize = Integer.parseInt(properties.getProperty("population.size", "100"));
        epochsCount = Integer.parseInt(properties.getProperty("epochs.count", "100"));
        pickingType = PickerType.valueOf(properties.getProperty("picking.type", "TOURNAMENT"));
        pickingTournamentParameter = Integer.parseInt(properties.getProperty("picking.tournament.parameter", "3"));
        selectionType = SelectorType.valueOf(properties.getProperty("selection.type", "ELITE"));
        selectionElitePart = new Probability(Double.parseDouble(properties.getProperty("selection.elite.part", "0.1")));
        mutationType = MutatorType.valueOf(properties.getProperty("mutation.type", "MANY_GENES"));
        mutationIndividualProbability = new Probability(Double.parseDouble(properties.getProperty("mutation.individual.probability", "0.3")));
        mutationGeneProbability = new Probability(Double.parseDouble(properties.getProperty("mutation.gene.probability", "0.15")));
        recombinerType = RecombinerType.valueOf(properties.getProperty("recombiner.type", "TWO_POINT"));
        recombinerManyCount = Integer.parseInt(properties.getProperty("recombiner.many.count", "3"));
    }

    public IndividualType getIndividualType() {
        return individualType;
    }

    public int getIndividualSize() {
        return individualSize;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getEpochsCount() {
        return epochsCount;
    }

    public PickerType getPickingType() {
        return pickingType;
    }

    public int getPickingTournamentParameter() {
        return pickingTournamentParameter;
    }

    public SelectorType getSelectionType() {
        return selectionType;
    }

    public Probability getSelectionElitePart() {
        return selectionElitePart;
    }

    public MutatorType getMutationType() {
        return mutationType;
    }

    public Probability getMutationIndividualProbability() {
        return mutationIndividualProbability;
    }

    public Probability getMutationGeneProbability() {
        return mutationGeneProbability;
    }

    public RecombinerType getRecombinerType() {
        return recombinerType;
    }

    public int getRecombinerManyCount() {
        return recombinerManyCount;
    }

    @Override
    public String toString() {
        return "AlgorithmProperties{" +
                "individualType=" + individualType +
                ", individualSize=" + individualSize +
                ", populationSize=" + populationSize +
                ", epochsCount=" + epochsCount +
                ", pickingType=" + pickingType +
                ", pickingTournamentParameter=" + pickingTournamentParameter +
                ", selectionType=" + selectionType +
                ", selectionElitePart=" + selectionElitePart +
                ", mutationType=" + mutationType +
                ", mutationIndividualProbability=" + mutationIndividualProbability +
                ", mutationGeneProbability=" + mutationGeneProbability +
                ", recombinerType=" + recombinerType +
                ", recombinerManyCount=" + recombinerManyCount +
                '}';
    }
}
