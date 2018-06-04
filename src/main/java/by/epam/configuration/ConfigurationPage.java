package by.epam.configuration;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationPage {

    private static final Logger LOGGER = LogManager.getLogger(ConfigurationPage.class);
    /**
     * Take string of bundle by key
     *
     * @param key to take bundle's string
     * @param bundleName to take bundle by bundle name
     * @return needed string of bundle
     */
    public static String getProperty(String key, String bundleName) {
        ResourceBundle resBundle;
        try {
            resBundle = ResourceBundle.getBundle(bundleName);
        } catch (Exception ex) {
            LOGGER.error("Resource bundle with name" + bundleName + "wasn't found", ex);
            throw new RuntimeException(ex);
        }
        return resBundle.getString(key);
    }
}
