package models.coding;

public class BinaryCoder extends Coder {
    public BinaryCoder(double minValue, double maxValue) {
        super(minValue, maxValue);
    }

    @Override
    public Boolean[] code(int size, double value) {
//        double locValue = value;
        final Boolean[] result = new Boolean[size];
        final double tmpVal1 = (maxValue - minValue);
        double divisor, tmpVal2;
        for (int i = 0; i < size; i++) {
            divisor = Math.pow(2.0, i + 1);
            tmpVal2 = tmpVal1 / divisor;
            if (value >= minValue + tmpVal2) {
                result[i] = true;
                value = value - tmpVal2;
            } else
                result[i] = false;
        }
        return result;
    }

    @Override
    public double decode(Object[] genes) {
        double result = minValue;
        final double tmpVal1 = maxValue - minValue;
        for (int i = 0; i < genes.length; i++) {
            if ((Boolean) genes[i])
                result += tmpVal1 / Math.pow(2.0, i + 1);
        }
        return result;
    }

    @Override
    public String toString() {
        return "BinaryCoder{" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
