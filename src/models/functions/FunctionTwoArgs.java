package models.functions;

import utils.ExceptionUtils;

public abstract class FunctionTwoArgs extends Function {
    @Override
    public double calculate(double... arguments) {
        if (arguments.length != 2)
            throw ExceptionUtils.valueNotEqualsAnotherValue(arguments.length, 2,
                    WRONG_ARGS_COUNT_MSG);
        return calculateInner(arguments[0], arguments[1]);
    }

    protected abstract double calculateInner(double argument1, double argument2);
}
