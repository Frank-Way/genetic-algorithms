package models.functions;

public abstract class Function {
    protected final static String WRONG_ARGS_COUNT_MSG = "Не верное количество аргументов";

    public abstract double calculate(double... arguments);
}
