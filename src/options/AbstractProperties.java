package options;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class AbstractProperties {
    protected final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    protected final static String userDir = System.getProperty("user.dir");

    protected final static String propertiesFileNamePrefix = userDir + File.separator + "src" + File.separator;

    protected static Properties readProperties(String propertiesFileName) throws IOException {
        try (InputStream input = Files.newInputStream(Paths.get(propertiesFileName))) {

            Properties properties = new Properties();
            properties.load(input);

            return properties;
        } catch (IOException ex) {
            logger.severe("Ошибка при загрузке параметров: " + ex);
            throw ex;
        }
    }
}