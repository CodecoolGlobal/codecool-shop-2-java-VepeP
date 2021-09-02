package com.codecool.shop.datahandler;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@WebServlet(name = "sendEmailServlet",
        urlPatterns = {"/sendEmail"},
        initParams = {@WebInitParam(name = "orderId", value = "")})
public class SendEmailServlet extends HttpServlet {

    private static final String USER_NAME = "programmingshop2021";
    private static final String PASSWORD = "wertzuiop123456789";

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String orderId = request.getParameter("orderId");
        if (orderId != null && !orderId.equals("")) {
            // TODO get data from order #orderID.json file
            String RECIPIENT = "programmingshop2021@gmail.com";
            String[] to = {RECIPIENT};
            String subject = "Java send mail example";
            String body = "hi ....,!";

            sendFromGMail(to, subject, body);
        }
    }

    private static void sendFromGMail(String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.password", SendEmailServlet.PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", SendEmailServlet.USER_NAME);

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {


            message.setFrom(new InternetAddress(SendEmailServlet.USER_NAME));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (InternetAddress address : toAddress) {
                message.addRecipient(Message.RecipientType.TO, address);
            }

            message.setSubject(subject);
            message.setText(body);


            Transport transport = session.getTransport("smtp");


            transport.connect(host, SendEmailServlet.USER_NAME, SendEmailServlet.PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException ae) {
            ae.printStackTrace();
        }
    }
}