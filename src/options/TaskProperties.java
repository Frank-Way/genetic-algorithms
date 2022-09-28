package options;

import java.io.IOException;
import java.util.Properties;

public class TaskProperties extends AbstractProperties {
    private final String functionName;
    private final int functionInputsCount;
    private final boolean functionInverted;
    private final double dataValueMin;
    private final double dataValueMax;


    public TaskProperties() throws IOException {
        this(propertiesFileNamePrefix + "task.properties");
    }

    public TaskProperties(String propertiesFileName) throws IOException {
        Properties properties = readProperties(propertiesFileName);
        functionName = properties.getProperty("function.name", "F1_1");

        functionInputsCount = Integer.parseInt(properties.getProperty("function.inputs.count", "1"));
        functionInverted = Boolean.parseBoolean(properties.getProperty("function.inverted", "false"));
        dataValueMin = Double.parseDouble(properties.getProperty("data.value.min", "-10"));
        dataValueMax = Double.parseDouble(properties.getProperty("data.value.max", "10"));
    }

    public String getFunctionName() {
        return functionName;
    }

    public int getFunctionInputsCount() {
        return functionInputsCount;
    }

    public boolean isFunctionInverted() {
        return functionInverted;
    }

    public double getDataValueMin() {
        return dataValueMin;
    }

    public double getDataValueMax() {
        return dataValueMax;
    }

    @Override
    public String toString() {
        return "TaskProperties{" +
                "functionName='" + functionName + '\'' +
                ", functionInputsCount=" + functionInputsCount +
                ", functionInverted=" + functionInverted +
                ", dataValueMin=" + dataValueMin +
                ", dataValueMax=" + dataValueMax +
                '}';
    }
}
