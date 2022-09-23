package options;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public class ApplicationProperties {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final static String userDir = System.getProperty("user.dir");
    private final static String propertiesFileName = userDir + File.separator + "src" +
            File.separator + "app.properties";

    private final boolean debugMode;

    public ApplicationProperties() throws IOException {
        this(propertiesFileName);
    }

    public ApplicationProperties(String propertiesFileName) throws IOException {
        try (InputStream input = Files.newInputStream(Paths.get(propertiesFileName))) {

            Properties properties = new Properties();
            properties.load(input);

            debugMode = Boolean.parseBoolean(properties.getProperty("debug.mode", "false"));
        } catch (IOException ex) {
            logger.severe("Ошибка при загрузке параметров: " + ex);
            throw ex;
        }
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
