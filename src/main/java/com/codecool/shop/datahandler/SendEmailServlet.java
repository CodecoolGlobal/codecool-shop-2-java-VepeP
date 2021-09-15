package com.codecool.shop.datahandler;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.FileHandler;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Properties;

@WebServlet(name = "sendEmailServlet",
        urlPatterns = {"/sendEmail"})
//initParams = {@WebInitParam(name = "orderId", value = "")})
public class SendEmailServlet extends HttpServlet {

    private static final String USER_NAME = "programmingshop2021";
    private static final String PASSWORD = "wertzuiop123456789";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {

        String orderId = request.getParameter("orderId");

        if (orderId != null && !orderId.equals("")) {
            CodecoolShopDbManager codecoolShopDbManager = new CodecoolShopDbManager();
            FileHandler fileHandler = new FileHandler();
            Order actOrder = fileHandler.getOrderFromFile(orderId);
            //CartDao cartDataStore = CartDaoMem.getInstance();
            CartDao cartDataStore = codecoolShopDbManager.getCartDao();
            String RECIPIENT = USER_NAME + "@gmail.com"; //actOrder.getEmail();
            String[] to = {RECIPIENT};
            String subject = "Order Confirmation (#" + orderId + ") from Programmer Shop";
            String body = getEmailBody(orderId, actOrder, cartDataStore);

            sendFromGMail(to, subject, body);

            cartDataStore.clearShoppingCart(1);
            fileHandler.saveFile(fileHandler.exportCartDao(), fileHandler.getCartFile());
        }
    }

    private String getEmailBody(String orderId, Order actOrder, CartDao cartDataStore) {
        StringBuilder emailBody = new StringBuilder("Dear " + /*actOrder.getName() +*/ "!\n" +
                "Your order has been received by Programmer shop.\n\n" +
                "Order number: " + orderId + "\n" +
                LocalDate.now() + "\n\n" +
                "Deliver to: \n" +
                /*actOrder.getName() + "\n" +*/
                /*actOrder.getAddress() + "\n" +*/
                /*actOrder.getCity() + "\n" +*/
                /*actOrder.getState() + " " + actOrder.getZip() + "\n\n" +*/
                "Products: " + "\n");
        for (CartProduct cartProduct :
                cartDataStore.getAll()) {
            emailBody.append(cartProduct.getName()).append(" ").append(cartProduct.getQuantity())
                    .append(" ").append(cartProduct.getDefaultPrice()).append("\n");
        }
        emailBody.append("\nBest regards,\n" + "Programming Shop");
        return emailBody.toString();
    }

    private void sendFromGMail(String[] to, String subject, String body) {
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