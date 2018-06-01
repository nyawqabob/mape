package by.epam.constant;

import by.epam.configuration.ConfigurationPage;

public class MailData {

    public static final String USER_GMAIL = ConfigurationPage.getProperty("user", "mail");
    public static final String PASSWORD_GMAIL = ConfigurationPage.getProperty("password", "mail");
    public static final String HOST_GMAIL = ConfigurationPage.getProperty("host", "mail");
    public static final String PORT_GMAIl = ConfigurationPage.getProperty("port", "mail");
}
