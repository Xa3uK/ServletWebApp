package net.servlet.soloservlets;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Mail extends HttpServlet {

    static final String SENDER_EMAIL_ADDRESS = "xa3ukxa3uk@gmail.com";
    static final String SENDER_EMAIL_PASSWORD = "tqbu xyos gwvi ehpr";
    static final String SENDER_HOST = "smtp.gmail.com";
    static final String SENDER_PORT = "587";
    static final String RECEIVER_EMAIL_ADDRESS = "kseniya2811grace@gmail.com";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", SENDER_HOST);
        properties.put("mail.smtp.port", SENDER_PORT);
        properties.put("mail.from", SENDER_EMAIL_ADDRESS);
        properties.put("mail.smtp.password", SENDER_EMAIL_PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
                    }
                });

        response.setContentType("text/html");

        String docType = "<!DOCTYPE html>";
        String title = "Send Email Demo";

        PrintWriter writer = response.getWriter();

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(RECEIVER_EMAIL_ADDRESS));
            message.setSubject("Java Tests");
            message.setText("java emeiliki test");
            Transport.send(message);
            String sendEmailResultMessage = "Email is successfully sent!";
            writer.println(docType + "<html>" +
                    "<head>" +
                    "<title>" + title + "</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>" + sendEmailResultMessage + "</h1>" +
                    "</body>" +
                    "</html>");


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
