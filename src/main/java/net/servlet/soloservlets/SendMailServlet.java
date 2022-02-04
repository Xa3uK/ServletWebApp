package net.servlet.soloservlets;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Properties;

public class SendMailServlet extends HttpServlet {

    static final String SENDER_EMAIL_ADDRESS = "xa3ukxa3uk@gmail.com";
    static final String SENDER_EMAIL_PASSWORD = "tqbu xyos gwvi ehpr";
    static final String SENDER_HOST = "smtp.gmail.com";
    static final String SENDER_PORT = "587";

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(req.getParameter("email")));
            message.setSubject(req.getParameter("subject"));
            message.setText(req.getParameter("message"));
            Transport.send(message);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("sendmail.jsp");
            requestDispatcher.forward(req, resp);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("sendmail.jsp");
        requestDispatcher.forward(req, resp);
    }
}

