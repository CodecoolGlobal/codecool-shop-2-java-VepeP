package com.codecool.shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutomatedTests {
    private static HomePage page;
    private static Payment pay;
    private static EmailService mail;
    private final String[] happyFormData = new String[]{"Molnár Petróniusz", "deeznutsshop666@gmail.com", "cica", "cica", "Pest", "1117", "Molnár Petróniusz", "1111222233334444", "October", "2021", "352"};

    @BeforeEach
    public void setup() {
        page = new HomePage();
        pay = new Payment();
        mail = new EmailService();
    }

    @Test
    public void completeOrder_happyPath() {
        String expected = "Codecool Shop";
        page.getPage();
        page.buyAndCheckout(page.getPeti(), page.getBalazs());
        pay.pay(happyFormData);

        assertEquals(expected, page.getTitle());

        mail.checkLatestMail();
        //page.pullUpCart();

        //assertEquals("Total: 0", page.getTotal());
    }

    @AfterEach
    public void tearDown() {
        page.closePage();
    }
}
