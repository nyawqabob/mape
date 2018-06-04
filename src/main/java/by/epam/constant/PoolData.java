package by.epam.constant;

import by.epam.configuration.ConfigurationPage;

public class PoolData {

    public static final String URL = ConfigurationPage.getProperty("db.url", "database");
    public static final String USER = ConfigurationPage.getProperty("db.user", "database");
    public static final String PASSWORD = ConfigurationPage.getProperty("db.password", "database");
    public static final String CHARACTER_ENCODING = ConfigurationPage.getProperty("db.characterEncoding", "database");
    public static final String USE_UNICODE = ConfigurationPage.getProperty("db.useUnicode", "database");
    public static final String POOL_SIZE = ConfigurationPage.getProperty("db.poolSize", "database");

}
