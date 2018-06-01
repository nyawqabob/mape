package by.epam.constant;

import by.epam.configuration.ConfigurationPage;

public final class Pages {

    public static final String ADMIN_PATH = ConfigurationPage.getProperty("path.page.admin", "config");
    public static final String USER_PATH = ConfigurationPage.getProperty("path.page.user", "config");
    public static final String LOGIN_PATH = ConfigurationPage.getProperty("path.page.login", "config");
    public static final String MAIN_PATH = ConfigurationPage.getProperty("path.page.main", "config");
    public static final String PUBLICATIONS_PATH = ConfigurationPage.getProperty("path.page.publications", "config");
    public static final String REDIRECT_PUBLICATIONS_PATH = ConfigurationPage.getProperty("path.page.redirectPublications", "config");
    public static final String USERS_PATH = ConfigurationPage.getProperty("path.page.users", "config");
    public static final String REDIRECT_USERS_PATH = ConfigurationPage.getProperty("path.page.redirectUsers", "config");
    public static final String PAYMENTS_PATH = ConfigurationPage.getProperty("path.page.payments", "config");
    public static final String ERROR_PATH = ConfigurationPage.getProperty("path.page.error", "config");

}
