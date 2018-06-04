package by.epam.mail;

import by.epam.constant.MailData;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
    private static MailSender instance;
    private static AtomicBoolean isCreated = new AtomicBoolean();
    private static Lock lock = new ReentrantLock();
    private static Lock anothLock = new ReentrantLock();
    private Session session;

    private MailSender() {
        session = Session.getInstance(getProperties(), null);
        session.setDebug(false);
    }

    public static MailSender getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new MailSender();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;

    }

    /**
     * Need to send new password to user mail
     *
     * @param sendTo this is email to send
     * @param username this is username with email under param sendTo
     * @param generatedPassword this is new password generated for user under
     * param username
     */
    public void sendNewPassword(String sendTo, String username, String generatedPassword) {
        anothLock.lock();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailData.USER_GMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject("New password at MAPE");
            message.setContent("<h1>Hello " + username + ". Your new password is" + generatedPassword + "</h1>", "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(MailData.HOST_GMAIL, MailData.USER_GMAIL, MailData.PASSWORD_GMAIL);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            LOGGER.info("Message to mail sended successfuly");
        } catch (MessagingException ex) {
            LOGGER.info("Message to mail wasn't sended", ex);
        } finally {
            anothLock.unlock();
        }
    }

    private Properties getProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", MailData.HOST_GMAIL);
        properties.put("mail.smtp.user", MailData.USER_GMAIL);
        properties.put("mail.smtp.password", MailData.PASSWORD_GMAIL);
        properties.put("mail.smtp.port", MailData.PORT_GMAIL);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.required", "true");
        return properties;
    }
}
