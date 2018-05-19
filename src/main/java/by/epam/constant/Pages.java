package by.epam.constant;

import by.epam.configuration.ConfigurationPage;

public final class Pages {

    public static final String ADMIN_PATH = ConfigurationPage.getProperty("path.page.admin");
    public static final String USER_PATH = ConfigurationPage.getProperty("path.page.user");
    public static final String LOGIN_PATH = ConfigurationPage.getProperty("path.page.login");
    public static final String MAIN_PATH = ConfigurationPage.getProperty("path.page.main");
    public static final String PUBLICATIONS_PATH = ConfigurationPage.getProperty("path.page.publications");
    public static final String REDIRECT_PUBLICATIONS_PATH = ConfigurationPage.getProperty("path.page.redirectPublications");
    public static final String USERS_PATH = ConfigurationPage.getProperty("path.page.users");
    public static final String REDIRECT_USERS_PATH = ConfigurationPage.getProperty("path.page.redirectUsers");
    public static final String PAYMENTS_PATH = ConfigurationPage.getProperty("path.page.payments");
    public static final String ERROR_PATH = ConfigurationPage.getProperty("path.page.error");
    
}
