package utils;

public abstract class ExceptionUtils {
    public static IllegalArgumentException newUnknownType(Class<?> type) {
        return new IllegalArgumentException("Не известный тип: " + type.getCanonicalName());
    }

    public static IllegalArgumentException newUnknownEnumItem(Enum<?> enumItem) {
        return new IllegalArgumentException("Не известный элемент перечисления: " + enumItem.toString());
    }

    public static IllegalArgumentException newIncompatibleTypes(Class<?> type1, Class<?> type2) {
        return new IllegalArgumentException("Не совместимые типы: " + type1.getCanonicalName() + " и " +
                type2.getCanonicalName());
    }

    public static IllegalArgumentException newEmptyCollection() {
        return new IllegalArgumentException("Пустая коллекция/массив");
    }

    public static IllegalArgumentException newValueOutOfRange(Number value, Number left, Number right) {
        return new IllegalArgumentException(String.format("Значение %s вне диапазона [%s; %s]",
                value, left, right));
    }
}
