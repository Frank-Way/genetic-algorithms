package models.functions;

import utils.ExceptionUtils;

public abstract class FunctionOneArg extends Function {
    @Override
    public double calculate(double... arguments) {
        if (arguments.length != 1)
            throw ExceptionUtils.valueNotEqualsAnotherValue(arguments.length, 1,
                    WRONG_ARGS_COUNT_MSG);
        return calculateInner(arguments[0]);
    }

    protected abstract double calculateInner(double argument);
}
