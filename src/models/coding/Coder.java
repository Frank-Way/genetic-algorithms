package models.coding;

import java.util.Arrays;

public abstract class Coder {
    protected final double minValue;
    protected final double maxValue;

    public Coder(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Object[] code(int size, double... values) {
        final Object[] result = new Object[size * values.length];
        Object[] codedValue = new Object[size];
        for (int i = 0; i < values.length; i++) {
            codedValue = code(size, values[i]);
            System.arraycopy(codedValue, 0, result, i * size, size);
        }
        return result;
    }

    public double[] decode(int size, Object... genes) {
        final int count = genes.length / size;
        final double[] result = new double[count];
//        int startPos = 0;
        for (int i = 0; i < count; i++) {
            result[i] = decode(Arrays.copyOfRange(genes, i * size, (i + 1) * size));
//            result[i] = decode(Arrays.copyOfRange(genes, startPos, startPos + size));
//            startPos += size;
        }
        return result;
    }

    protected abstract double decode(Object... genes);

    protected abstract Object[] code(int size, double value);

    @Override
    public abstract String toString();
}
