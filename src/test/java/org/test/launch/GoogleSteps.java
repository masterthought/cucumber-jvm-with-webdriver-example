package org.test.launch;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.test.web.JZDriver;
import org.test.web.JZDrivers;
import org.test.web.page.objects.GmailHomePage;
import org.test.web.page.objects.GmailInboxPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GoogleSteps {

    private JZDriver currentJzDriver;
    GmailInboxPage gmailInboxPage;

    @Given("^I have opened my browser No (\\d+)$")
    public void I_have_opened_my_browser(Integer browserNumber) throws Exception {
        currentJzDriver = JZDrivers.getJzDriverInstance(browserNumber);
    }

    @Given("^I am using browser No (\\d+)$")
    public void I_am_using_browser(Integer integer) throws Exception {
        setFocusOnWebDriver(integer);
    }

    @Given("^I visit (.+)$")
    public void I_visit(String s) throws Exception {
        currentJzDriver.getWebDriver().get(s);
    }

    private void setFocusOnWebDriver(Integer browserNumber) throws Exception {
        try {
            currentJzDriver = JZDrivers.getJzDriverInstance(browserNumber);
        } catch (UnreachableBrowserException e) {
            currentJzDriver = JZDrivers.resetAndGet(browserNumber);
        }
    }

    @Given("^I login with username:'(.*)' and password '(.*)'$")
    public void login_with_username_and_password(String username, String password) {
        try {
            GmailHomePage homePage = new GmailHomePage(currentJzDriver);
            gmailInboxPage = homePage.loginAs(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test aborted.");
            org.junit.Assert.assertFalse(true);
        }
    }

    @Then("^I have (\\d+) Browser instances$")
    public void I_have_Browser_instances(int browserInstanceCount) {
        assertThat(browserInstanceCount, is(JZDrivers.getJzDrivers().size()));
    }

    @Then("^login was successful$")
    public void loginWasSuccessful() throws Exception {
        assertThat(gmailInboxPage.isLoginSuccessful(), is(true));
    }

    @Then("^login was unsuccessful$")
    public void loginWasUnsuccessful() {
        assertThat(gmailInboxPage.isLoginSuccessful(), is(false));
    }

    @And("^I have no Browser instances$")
    public void I_have_no_Browser_instances() {
        JZDrivers.clearDrivers();
        I_have_Browser_instances(0);
    }

}
