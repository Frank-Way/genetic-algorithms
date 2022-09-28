package options;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties extends AbstractProperties {
    private final boolean debugMode;

    public ApplicationProperties() throws IOException {
        this(propertiesFileNamePrefix + "application.properties");
    }

    public ApplicationProperties(String propertiesFileName) throws IOException {
        Properties properties = readProperties(propertiesFileName);
        debugMode = Boolean.parseBoolean(properties.getProperty("debug.mode", "false"));
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    @Override
    public String toString() {
        return "AppProperties{" +
                "debugMode=" + debugMode +
                '}';
    }
}
