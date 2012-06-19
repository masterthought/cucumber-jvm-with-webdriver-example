package org.test.web;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JZDriver {

    public WebDriver getWebDriver() {
        return webDriver;
    }

    private WebDriver webDriver;
    private int defaultWaitTimeInSeconds = 10;

    public JZDriver(WebDriver webDriver, int defaultWaitTimeInSeconds) {
        this.defaultWaitTimeInSeconds = defaultWaitTimeInSeconds;
        this.webDriver = webDriver;
    }

    public JZDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement waitForElementByName(String elementName, int timeoutInSeconds, boolean silent) throws Exception {
        return waitUntilLocatorFound(By.id(elementName), timeoutInSeconds, silent);
    }

    public WebElement waitForElementByClass(String className, int timeoutInSeconds, boolean silent) throws Exception {
        return waitUntilLocatorFound(By.className(className), timeoutInSeconds, silent);
    }

    private WebElement waitUntilLocatorFound(By locator, int timeoutInSeconds, boolean silent) throws Exception {
        try {
            return new WebDriverWait(webDriver, timeoutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            handleException(e, silent);
        } catch (TimeoutException e) {
            handleException(e, silent);
        }
        return null;
    }

    private void handleException(Exception e, boolean silent) throws Exception {
        if (!silent) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public void waitForTitleToContain(String title, boolean silent) throws Exception {
        try {
            new WebDriverWait(webDriver, defaultWaitTimeInSeconds).until(ExpectedConditions.titleContains(title));
        } catch (Exception e) {
            handleException(e, silent);
        }
    }

    public void clearCache() throws Exception {
        String clearCacheJavascript = "" +
                "Response.Cache.SetExpires(DateTime.Parse(DateTime.Now.ToString()))\n" +
                "Response.Cache.SetCacheability(HttpCacheability.Private)\n" +
                "Response.Cache.SetNoStore()\n" +
                "Response.AppendHeader(\"Pragma\", \"no-cache\")";
        ((JavascriptExecutor) webDriver).executeScript(clearCacheJavascript);
        webDriver.navigate().refresh();
    }

    public WebElement findElement(By locator, boolean silent) throws Exception {
        try {
            return webDriver.findElement(locator);
        } catch (Exception e) {
            handleException(e, silent);
        }
        return null;
    }
}
