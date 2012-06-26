package org.test.web.page.objects;

import com.natpryce.makeiteasy.Maker;
import org.openqa.selenium.By;
import org.test.web.JZDriver;
import org.test.web.org.test.email.Email;

import static com.natpryce.makeiteasy.MakeItEasy.a;

public class GmailInboxPage {

    private JZDriver jzDriver;
    private boolean loginSuccessful = false;

    public GmailInboxPage(JZDriver jzDriver, String username, String password) throws Exception {
        this.jzDriver = jzDriver;
        while (jzDriver.getWebDriver().getTitle().equals("Gmail")) {
            System.out.println("Waiting for mail page to render fully");
            Thread.sleep(1000);
        }

        if (jzDriver.getWebDriver().getTitle().contains("Inbox")) logout();
        jzDriver.waitForElementByName("Email", 10, false).sendKeys(username);
        jzDriver.getWebDriver().findElement(By.name("Passwd")).sendKeys(password);
        jzDriver.getWebDriver().findElement(By.name("signIn")).click();
        if (!loginFailed(jzDriver)) {
            System.out.println("Retrieving Inbox");
            jzDriver.waitForTitleToContain("Inbox", false);
            loginSuccessful = true;
        }
        System.out.println("login success was " + loginSuccessful + " using username=" + username + " and password=" + password);
    }

    public void logout() throws Exception {
        System.out.println("logging out of mail");
        jzDriver.getWebDriver().get("https://mail.google.com/mail/u/0/?logout&hl=en");
    }

    public void composeNewEmail() {
        Maker<Email> email = a(org.test.web.org.test.email.EmailBuilder.Email);
    }


    private boolean loginFailed(JZDriver jzDriver) throws Exception {
        return (jzDriver.findElement(By.className("errormsg"), true) != null || jzDriver.findElement(By.className("captcha-img"), true) != null);
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}
