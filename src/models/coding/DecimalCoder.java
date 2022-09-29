package models.coding;

public class DecimalCoder extends Coder {
    public DecimalCoder(double minValue, double maxValue) {
        super(minValue, maxValue);
    }

    @Override
    public Integer[] code(int size, double value) {
        return new Integer[0];
    }

    @Override
    public double decode(Object[] genes) {
        return 0;
    }

    @Override
    public String toString() {
        return "DecimalCoder{" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
