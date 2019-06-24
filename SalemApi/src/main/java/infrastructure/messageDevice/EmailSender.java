package infrastructure.messageDevice;

import domain.account.Account;
import domain.messageDevice.MessageSender;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender implements MessageSender {

    private String SENDER = "billetaul@gmail.com";
    private  String USERNAME = "billetaul";
    private String PASSWORD = "glo4003archi";
    private String SUBJECT = "Paiement de ticket";
    private String SMTP_HOST = "mail.smtp.host";
    private String SMTP_GMAIL = "smtp.gmail.com";
    private String SMTP_AUTH = "mail.smtp.auth";
    private String SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private String SMTP_PORT = "mail.smtp.port";
    private String SMTP_PORT_NUMBER = "587";
    private  String TRUE = "true";

    @Override
    public void sendMessage(String recipient, String emailText) throws Exception {
        Properties properties =  System.getProperties();
        properties.put(SMTP_HOST, SMTP_GMAIL);
        properties.put(SMTP_AUTH, TRUE);
        properties.put(SMTP_STARTTLS, TRUE);
        properties.put(SMTP_PORT, SMTP_PORT_NUMBER);

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME,PASSWORD);
                    }
                });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(SUBJECT);
        message.setText(emailText);
        Transport.send(message);
    }

}
