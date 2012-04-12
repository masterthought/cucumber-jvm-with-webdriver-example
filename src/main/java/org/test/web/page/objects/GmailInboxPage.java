package org.test.web.page.objects;

import com.natpryce.makeiteasy.Maker;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.test.web.org.test.email.Email;

import static com.natpryce.makeiteasy.MakeItEasy.a;

/**
 * Created by IntelliJ IDEA.
 * User: kostasmamalis
 * Date: 26/03/2012
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class GmailInboxPage extends AbstractPageObject{


    public GmailInboxPage(WebDriver webDriver) {
        super(webDriver);

        int waitSeconds = 20;
        boolean found = false;
        while(waitSeconds != 0){
            try {
                webDriver.getTitle().contains("Inbox");
                found = true;
                break;
            } catch(org.openqa.selenium.NoSuchElementException nse){
                 waitSeconds--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!found){
             throw new NoSuchFrameException("element with className=\"aam\" wasn't found");
        }
    }

    public void composeNewEmail(){
           Maker<Email> email = a(org.test.web.org.test.email.EmailBuilder.Email);
    }


}
