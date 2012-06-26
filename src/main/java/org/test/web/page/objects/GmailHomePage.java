package org.test.web.page.objects;

import org.test.web.JZDriver;

public class GmailHomePage {

    public static final String url = "http://mail.google.com";
    private JZDriver jzDriver;

    public GmailHomePage(JZDriver jzDriver) throws Exception {
        this.jzDriver = jzDriver;
        this.jzDriver.getWebDriver().get(url);
    }

    public GmailInboxPage loginAs(String username, String password) throws Exception {
        return new GmailInboxPage(jzDriver, username, password);
    }

}
