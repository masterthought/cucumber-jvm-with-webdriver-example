package org.test.web.page.objects;

import org.openqa.selenium.WebDriver;
import org.test.web.JZDriver;

public class GmailHomePage extends AbstractPageObject{

   public static final String url = "http://mail.google.com";
   public static final int expireTime = 10;
    public GmailHomePage(WebDriver webDriver){
          super(webDriver);
          this.webDriver.get(url);
    }

    public GmailInboxPage loginAs(String username, String password) throws InterruptedException {
        int waitSeconds = 10;
        JZDriver.findElement("Email").sendKeys(username);
        JZDriver.findElement("Passwd").sendKeys(password);
        JZDriver.findElement("signIn").click();
        return new GmailInboxPage(webDriver);
    }

}
