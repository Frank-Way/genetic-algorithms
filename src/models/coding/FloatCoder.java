package models.coding;

public class FloatCoder extends Coder {
    public FloatCoder(double minValue, double maxValue) {
        super(minValue, maxValue);
    }

    @Override
    public Double[] code(int size, double value) {
        return new Double[0];
    }

    @Override
    public double decode(Object[] genes) {
        return 0;
    }

    @Override
    public String toString() {
        return "FloatCoder{" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
