package org.test.web.page.objects;

import org.openqa.selenium.WebDriver;

/**
 * Created by IntelliJ IDEA.
 * User: kostasmamalis
 * Date: 26/03/2012
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class AbstractPageObject {

    protected WebDriver webDriver;

    public AbstractPageObject(WebDriver webDriver) {
         this.webDriver = webDriver;
    }

    public WebDriver getWebDriver(){
        return this.webDriver;
    }

    public void setWebDriver(WebDriver webDriver){
        this.webDriver = webDriver;
    }

}
