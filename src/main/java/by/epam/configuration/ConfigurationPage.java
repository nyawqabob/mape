
package by.epam.configuration;

import java.util.ResourceBundle;

public class ConfigurationPage {

    private static final ResourceBundle resBundle = ResourceBundle.getBundle("config");

    /**
     * Take string of bundle by key
     * @param key to take bundle's string
     * @return needed string of bundle
     */
    public static String getProperty(String key) {
        return resBundle.getString(key);
    }
}
