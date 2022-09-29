package models;

import utils.ExceptionUtils;

import java.util.Random;

public class Probability {
    private static final double minValue = 0.0;
    private static final double maxValue = 1.0;
    private static final Random random = new Random();
    private final double value;

    public Probability(double value) {
        check(value);
        this.value = value;
    }

    private static void check(double value) {
        if (!(minValue <= value && value <= maxValue))
            throw ExceptionUtils.valueOutOfRange(value, minValue, maxValue);
    }

    public static Probability getRandom() {
        return new Probability(random.nextDouble());
    }

    @Override
    public String toString() {
        return "Probability{" +
                "value=" + value +
                '}';
    }

    public double getValue() {
        return value;
    }

    public boolean isFired() {
        return random.nextDouble() <= value;
    }
}
