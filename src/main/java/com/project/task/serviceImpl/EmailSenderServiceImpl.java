package com.project.task.serviceImpl;

import com.project.task.dto.EmailDetails;
import com.project.task.service.EmailSenderService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private static final Properties PROPERTIES = new Properties();
    private static final String USERNAME = "kevin11barata@gmail.com";   //change it
    private static final String PASSWORD = "ljea eaoo zkzs hfus";   //change it

    private static final String FROM = "task.test@gmail.com";   //change it

    static {
        PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
        PROPERTIES.put("mail.smtp.port", "587");
        PROPERTIES.put("mail.smtp.auth", "true");
        PROPERTIES.put("mail.smtp.starttls.enable", "true");
    }

    public int sendEmail(EmailDetails emailDetails) {

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };

        Session session = Session.getInstance(PROPERTIES, authenticator);
        session.setDebug(true);

        try {

            // create a message with headers
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(emailDetails.getTo())};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(emailDetails.getSubject());
            msg.setSentDate(new Date());

            // create message body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailDetails.getMessage(), "text/html"); // Set konten HTML
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // set message content
            msg.setContent(multipart);


            // send the message
            Transport.send(msg);
            return 1;

        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

}