package utils;

import java.util.*;
import java.util.stream.Collectors;

public abstract class CollectionUtils {
    public static <T> boolean contains(T value, T[] array) {
        return Arrays.asList(array).contains(value);
    }

    public static <T> int find(T value, T[] array) {
        for (int i = 0; i < array.length; i++)
            if (value.equals(array[i]))
                return i;
        return -1;
    }

    public static int[] sort(int[] array) {
        return sort(array, SortType.ASC);
    }

    public static int[] sort(int[] array, SortType sortType) {
        return sort(Arrays.stream(array).boxed().collect(Collectors.toList()), sortType)
                .stream().mapToInt(i -> i).toArray();
    }

    public static List<Integer> sort(List<Integer> list, SortType sortType) {
        if (sortType.equals(SortType.DESC))
            return list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        return list.stream().sorted().collect(Collectors.toList());
    }

    public enum SortType {
        ASC,
        DESC
    }

}
