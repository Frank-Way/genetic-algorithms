package utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public abstract class RandomUtils {
    private final static Random random = new Random();

    public static int get(int minValue, int maxValue) {
        return minValue + random.nextInt(maxValue - minValue);
    }

    public static Integer getBoxed(Integer minValue, Integer maxValue) {
        return get(minValue, maxValue);
    }

    public static double get(double minValue, double maxValue) {
        return minValue + random.nextDouble() * (maxValue - minValue);
    }

    public static Double getBoxed(Double minValue, Double maxValue) {
        return get(minValue, maxValue);
    }

    public static boolean get() {
        return random.nextBoolean();
    }

    public static Boolean getBoxed() {
        return random.nextBoolean();
    }

    public static int[] get(int minValue, int maxValue, int count) {
        return IntStream.range(0, count).map(i -> get(minValue, maxValue)).toArray();
    }

    public static Integer[] getBoxed(Integer minValue, Integer maxValue, int count) {
        return IntStream.range(0, count).mapToObj(i -> getBoxed(minValue, maxValue)).toArray(Integer[]::new);
    }

    public static double[] get(double minValue, double maxValue, int count) {
        return IntStream.range(0, count).mapToDouble(i -> get(minValue, maxValue)).toArray();
    }

    public static Double[] getBoxed(Double minValue, Double maxValue, int count) {
        return IntStream.range(0, count).mapToObj(i -> getBoxed(minValue, maxValue)).toArray(Double[]::new);
    }

    public static boolean[] get(int count) {
        final boolean[] result = new boolean[count];
        IntStream.range(0, count).forEach(i -> result[i] = get());
        return result;
    }

    public static Boolean[] getBoxed(int count) {
        final Boolean[] result = new Boolean[count];
        IntStream.range(0, count).forEach(i -> result[i] = getBoxed());
        return result;
    }

    public static int[] getDistinct(int minValue, int maxValue, int count) {
        Set<Integer> integers = new HashSet<>();
        while (integers.size() < count)
            integers.add(get(minValue, maxValue));
        return integers.stream().mapToInt(i -> i).toArray();
    }

    public static Integer[] getDistinctBoxed(Integer minValue, Integer maxValue, int count) {
        Set<Integer> integers = new HashSet<>();
        while (integers.size() < count)
            integers.add(getBoxed(minValue, maxValue));
        return integers.stream().toArray(Integer[]::new);
    }

    public static double[] getDistinct(double minValue, double maxValue, int count) {
        Set<Double> doubles = new HashSet<>();
        while (doubles.size() < count)
            doubles.add(get(minValue, maxValue));
        return doubles.stream().mapToDouble(i -> i).toArray();
    }

    public static Double[] getDistinctBoxed(Double minValue, Double maxValue, int count) {
        Set<Double> doubles = new HashSet<>();
        while (doubles.size() < count)
            doubles.add(get(minValue, maxValue));
        return doubles.stream().toArray(Double[]::new);
    }
}
