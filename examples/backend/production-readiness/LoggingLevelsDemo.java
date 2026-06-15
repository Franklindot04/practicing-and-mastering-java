import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingLevelsDemo {
    private static final Logger LOGGER = Logger.getLogger(LoggingLevelsDemo.class.getName());

    public static void main(String[] args) {
        LOGGER.finest("TRACE-style detail: usually too noisy outside local debugging.");
        LOGGER.fine("DEBUG-style detail: useful while investigating behavior.");
        LOGGER.info("INFO: application started with safe demo configuration.");
        LOGGER.warning("WARN: optional recommendation service is unavailable.");
        LOGGER.log(Level.SEVERE, "ERROR: required operation failed.");
    }
}
