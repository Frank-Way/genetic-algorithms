import models.Trainer;
import options.AlgorithmProperties;
import options.ApplicationProperties;
import options.TaskProperties;

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

        final ApplicationProperties applicationProperties;
        final AlgorithmProperties algorithmProperties;
        final TaskProperties taskProperties;
        try {
            applicationProperties = new ApplicationProperties();
            algorithmProperties = new AlgorithmProperties();
            taskProperties = new TaskProperties();
        } catch (IOException e) {
            logger.severe(e.getMessage());
            return;
        }

        Trainer trainer = new Trainer();
        trainer.train(applicationProperties, algorithmProperties, taskProperties);
    }
}