package utils;

import java.util.Optional;

public abstract class Utils {
    /**
     * Получение потока по его ID
     * @param threadId ID потока
     * @return         поток
     */
    public static Optional<Thread> getThread(long threadId) {
        return Thread.getAllStackTraces().keySet().stream()
                .filter(t -> t.getId() == threadId)
                .findFirst();
    }
}
