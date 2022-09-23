import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        // чтение настроек логгирования
        try {
            LogManager logManager = LogManager.getLogManager();
            Class<Main> aClass = Main.class;
            InputStream inputStream = aClass.getResourceAsStream("logging.properties");
            logManager.readConfiguration(inputStream);
            logger.fine("Успешно считаны конфигурации для логгеров");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}