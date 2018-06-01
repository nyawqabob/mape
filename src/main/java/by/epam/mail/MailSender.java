package by.epam.mail;

import by.epam.command.base.RestorePasswordCommand;
import by.epam.constant.MailData;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailSender {

    private static final Logger LOGGER = LogManager.getLogger(MailSender.class);

    /**
     *
     * @param sendTo this is email to send
     * @param username this is username with email under param sendTo
     * @param generatedPassword this is new password generated for user under param username
     */
    public void sendNewPassword(String sendTo, String username, String generatedPassword) {
        boolean sessionDebug = false;
        Session session = Session.getInstance(getProperties(), null);

        try {
            session.setDebug(sessionDebug);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailData.USER_GMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject("New password at MAPE");
            message.setContent("<h1>Hello " + username + ". Your new password is" + generatedPassword + "</h1>", "text/html");

            Transport transport = session.getTransport("smtp");
            transport.connect(MailData.HOST_GMAIL, MailData.USER_GMAIL, MailData.PASSWORD_GMAIL);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException ex) {
            LOGGER.info("Message to mail wasn't sended", ex);
        }
    }

    private Properties getProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", MailData.HOST_GMAIL);
        properties.put("mail.smtp.user", MailData.USER_GMAIL);
        properties.put("mail.smtp.password", MailData.PASSWORD_GMAIL);
        properties.put("mail.smtp.port", MailData.PORT_GMAIl);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.required", "true");
        return properties;
    }
}
