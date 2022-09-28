package models.functions;

import models.functions.impl.F1_1;
import models.functions.impl.F2_1;
import utils.ExceptionUtils;

public class FunctionFactory {
    public Function create(String className) {
        if (className.equals("F1_1"))
            return new F1_1();
        else if (className.equals("F2_1"))
            return new F2_1();
        else
            throw ExceptionUtils.unknownClass(className);
    }

    public FunctionOneArg createOneArg(String className) {
        if (className.equals("F1_1"))
            return new F1_1();
        throw ExceptionUtils.unknownClass(className, "или функция не одного аргумента");
    }

    public FunctionTwoArgs createTwoArgs(String className) {
        if (className.equals("F2_1"))
            return new F2_1();
        throw ExceptionUtils.unknownClass(className, "или функция не двух аргументов");
    }
}
