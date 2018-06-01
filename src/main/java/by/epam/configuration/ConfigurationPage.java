package by.epam.configuration;

import java.util.ResourceBundle;

public class ConfigurationPage {
    /**
     * Take string of bundle by key
     * @param key to take bundle's string
     * @param bundleName to take bundle by bundle name
     * @return needed string of bundle
     */
    public static String getProperty(String key, String bundleName) {
        ResourceBundle resBundle = ResourceBundle.getBundle(bundleName);
        return resBundle.getString(key);
    }
}
