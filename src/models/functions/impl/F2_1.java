package models.functions.impl;

import models.functions.FunctionTwoArgs;

/**
 * z = f(x, y) = 2 exp(- x^2 - y^2) + sin(x + 9y)
 */
public class F2_1 extends FunctionTwoArgs {
    @Override
    protected double calculateInner(double argument1, double argument2) {
        return 2 * Math.exp(-argument1 * argument1 - argument2 * argument2) +
                Math.sin(argument1 + 9 * argument2);
    }

    @Override
    public String toString() {
        return "F2_1{z = f(x, y) = 2 exp(- x^2 - y^2) + sin(x + 9y)}";
    }
}
