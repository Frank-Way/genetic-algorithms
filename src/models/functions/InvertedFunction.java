package models.functions;

public class InvertedFunction extends Function {
    private final Function source;

    public InvertedFunction(Function source) {
        this.source = source;
    }

    @Override
    public double calculate(double... arguments) {
        return -1.0 * source.calculate(arguments);
    }
}
