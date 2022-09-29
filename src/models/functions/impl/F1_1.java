package models.functions.impl;

import models.functions.FunctionOneArg;

/**
 * y = f(x) = x^4 - x^2 + 3
 */
public class F1_1 extends FunctionOneArg {
    @Override
    protected double calculateInner(double argument) {
        return argument * argument * argument * argument -
                argument * argument +
                3;
    }

    @Override
    public String toString() {
        return "F1_1{y = f(x) = x^4 - x^2 + 3}";
    }
}
