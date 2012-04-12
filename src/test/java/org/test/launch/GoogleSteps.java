package org.test.launch;

import cucumber.annotation.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.test.web.JZDriver;
import org.test.web.page.objects.GmailHomePage;
import org.test.web.page.objects.GmailInboxPage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: kostasmamalis
 * Date: 26/03/2012
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class GoogleSteps {

    private WebDriver mDriver;
    private GmailHomePage homePage;
    private GmailInboxPage resultsPage;
    private Integer driverPointer;

    private static Map<Integer,WebDriver> drivers = new HashMap<Integer, WebDriver>();


    @Given("^I have opened my browser No.(\\d+)$")
    public void I_have_opened_my_browser(Integer integer) throws Exception {
        driverPointer = integer;
        goToHomePage(driverPointer);
    }

    @Given("^I am using browser No.(\\d+)$")
    public void I_am_using_browser(Integer integer) throws Exception{
        setFocusOnWebDriver(integer);
    }

     @Given("^I visit (.+)$")
     public void I_visit(String s) throws Exception{
        mDriver.get(s);
         Thread.sleep(3000);
    }


    private void goToHomePage(Integer i) throws Exception {
        try {
            mDriver = JZDriver.getCurrent(i);
            homePage = new GmailHomePage(mDriver);
        } catch(UnreachableBrowserException e){
            mDriver = JZDriver.resetAndGet(i);
            homePage.setWebDriver(mDriver);
        }
    }

    private void setFocusOnWebDriver(Integer i) throws Exception{
         try {
             driverPointer = i;
             mDriver = JZDriver.getCurrent(i);
        } catch(UnreachableBrowserException e){
             driverPointer = i;
             mDriver = JZDriver.resetAndGet(i);
        }
    }

    @Given("^I login with username:'(.+)' and password '(.+)'$")
    public void login_with_username_and_password(String username, String password){
        try {
            resultsPage = homePage.loginAs(username, password);
        } catch (InterruptedException e) {
            System.out.println("Test aborted.");
            org.junit.Assert.assertFalse(true);
        }
    }
}
