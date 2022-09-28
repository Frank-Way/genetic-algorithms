package utils;

public abstract class ExceptionUtils {
    private static IllegalArgumentException newIllegalArgumentException(String message) {
        return new IllegalArgumentException(message);
    }

    private static IllegalArgumentException newIllegalArgumentException(String message, String additionalMessage) {
        return newIllegalArgumentException(message + " [" + additionalMessage + "]");
    }

    public static IllegalArgumentException unknownType(Class<?> type) {
        return newIllegalArgumentException("Не известный тип: " + type.getCanonicalName());
    }

    public static IllegalArgumentException unknownType(Class<?> type, String message) {
        return newIllegalArgumentException("Не известный тип: " + type.getCanonicalName(), message);
    }

    public static IllegalArgumentException unknownEnumItem(Enum<?> enumItem) {
        return newIllegalArgumentException("Не известный элемент перечисления: " + enumItem.toString());
    }

    public static IllegalArgumentException unknownEnumItem(Enum<?> enumItem, String message) {
        return newIllegalArgumentException("Не известный элемент перечисления: " + enumItem.toString(), message);
    }

    public static IllegalArgumentException incompatibleTypes(Class<?> type1, Class<?> type2) {
        return newIllegalArgumentException("Не совместимые типы: " + type1.getCanonicalName() + " и " +
                type2.getCanonicalName());
    }

    public static IllegalArgumentException incompatibleTypes(Class<?> type1, Class<?> type2, String message) {
        return newIllegalArgumentException("Не совместимые типы: " + type1.getCanonicalName() + " и " +
                type2.getCanonicalName(), message);
    }

    public static IllegalArgumentException emptyCollection() {
        return newIllegalArgumentException("Пустая коллекция/массив");
    }

    public static IllegalArgumentException emptyCollection(String message) {
        return newIllegalArgumentException("Пустая коллекция/массив", message);
    }

    public static IllegalArgumentException collectionOfUnexpectedSize(int actualSize, int expectedSize) {
        return newIllegalArgumentException(String.format("Коллекция размера %d, когда ожидался размер %d", actualSize, expectedSize));
    }

    public static IllegalArgumentException collectionOfUnexpectedSize(int actualSize, int expectedSize, String message) {
        return newIllegalArgumentException(String.format("Коллекция размера %d, когда ожидался размер %d", actualSize, expectedSize), message);
    }

    public static IllegalArgumentException valueOutOfRange(Number value, Number left, Number right) {
        return newIllegalArgumentException(String.format("Значение %s вне диапазона [%s; %s]",
                value, left, right));
    }

    public static IllegalArgumentException valueOutOfRange(Number value, Number left, Number right, String message) {
        return newIllegalArgumentException(String.format("Значение %s вне диапазона [%s; %s]",
                value, left, right), message);
    }

    public static IllegalArgumentException valueLessThanThreshold(Number value, Number threshold) {
        return newIllegalArgumentException(String.format("Значение %s меньше %s", value, threshold));
    }

    public static IllegalArgumentException valueLessThanThreshold(Number value, Number threshold, String message) {
        return newIllegalArgumentException(String.format("Значение %s меньше %s", value, threshold), message);
    }

    public static IllegalArgumentException valueGreaterThanThreshold(Number value, Number threshold) {
        return newIllegalArgumentException(String.format("Значение %s больше %s", value, threshold));
    }

    public static IllegalArgumentException valueGreaterThanThreshold(Number value, Number threshold, String message) {
        return newIllegalArgumentException(String.format("Значение %s больше %s", value, threshold), message);
    }

    public static IllegalArgumentException valueEqualsAnotherValue(Number value, Number anotherValue) {
        return newIllegalArgumentException(String.format("Значение %s равно %s", value, anotherValue));
    }

    public static IllegalArgumentException valueEqualsAnotherValue(Number value, Number anotherValue, String message) {
        return newIllegalArgumentException(String.format("Значение %s равно %s", value, anotherValue), message);
    }

    public static IllegalArgumentException valueNotEqualsAnotherValue(Number value, Number anotherValue) {
        return newIllegalArgumentException(String.format("Значение %s не равно %s", value, anotherValue));
    }

    public static IllegalArgumentException valueNotEqualsAnotherValue(Number value, Number anotherValue, String message) {
        return newIllegalArgumentException(String.format("Значение %s не равно %s", value, anotherValue), message);
    }

    public static IllegalArgumentException unknownClass(String className) {
        return newIllegalArgumentException("Не известный класс: " + className);
    }

    public static IllegalArgumentException unknownClass(String className, String message) {
        return newIllegalArgumentException("Не известный класс: " + className, message);
    }
}
