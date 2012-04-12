package org.test.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: kostasmamalis
 * Date: 19/03/2012
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */

public class JZDriver {

    private static WebDriver webDriver;
    private static Map<Integer, WebDriver> webDrivers;

    public static int expireTime = 10;

    protected JZDriver(){

    }

    public static void clearWebDrivers(){
        webDrivers.clear();
    }

    public static WebDriver getCurrentDriver() throws Exception{
        try{
           return webDriver == null ? webDriver = new FirefoxDriver() : webDriver ;
        } catch(UnreachableBrowserException e) {
            webDriver = new FirefoxDriver();
            return webDriver;
        }
    }

    public static WebDriver getCurrent(Integer i) throws Exception{
        WebDriver result;
        try{
           if(webDrivers == null) webDrivers = new HashMap<Integer, WebDriver>();
            System.out.println("using browser:" + i);
            result = createIfNotExists(i);
        } catch(UnreachableBrowserException e){
            removeAndReplace(i);
        } finally {
            result = webDrivers.get(i) ;
            return result;
        }

    }

    private static WebDriver createIfNotExists(Integer i) throws Exception {
        WebDriver result = null;
        if(webDrivers.get(i) == null) {
                System.out.println("The browser No." + i + " was null. Creating a new one");
                result = new FirefoxDriver();
                webDrivers.put(i,result);
            }
        else {
            result = webDrivers.get(i);
        }

        if(result.getTitle() == null || result.getTitle().trim().equals("")){
               System.out.println("Webdriver " + i + " isn't pointing into any sites.");
            } else {
                System.out.println("Webdriver " + i + " is pointing to: " + result.getTitle());
            }
        return result;
    }


    private static void removeAndReplace(Integer i) throws Exception {
          System.out.println("Removing browser No." + i + " and putting a new empty one in its place");
            webDrivers.remove(i);
            webDrivers.put(i,new FirefoxDriver());
    }

    public static Map<Integer, WebDriver> getWebDrivers() throws Exception{
        return webDrivers;
    }

    public static WebDriver resetAndGet() throws Exception{
        webDriver = new FirefoxDriver();
        //clearCache();
            return webDriver;
    }

    public static WebDriver resetAndGet(Integer i) throws Exception{
        if(webDrivers == null) webDrivers = new HashMap<Integer, WebDriver>();

        if(webDrivers.get(i) == null) {
            System.out.println("RESET: The browser No." + i + " was null");
            webDrivers.put(i,new FirefoxDriver());
        }
        webDriver = webDrivers.get(i);
        //clearCache();
            return webDriver;
    }

    public static WebElement findElement(String id) throws InterruptedException{
        WebElement webElement = null;
        int waitSeconds = expireTime;
        while(waitSeconds != 0){
            try {
                webElement = webDriver.findElement(By.id(id));
                break;
            } catch(org.openqa.selenium.NoSuchElementException nse){
                 waitSeconds--;
                 Thread.sleep(1000);
            }
        }
        return webElement;
    }

    public static void clearCache() throws Exception{
        String clearCacheJavascript = "" +
            "Response.Cache.SetExpires(DateTime.Parse(DateTime.Now.ToString()))\n" +
            "Response.Cache.SetCacheability(HttpCacheability.Private)\n" +
            "Response.Cache.SetNoStore()\n" +
            "Response.AppendHeader(\"Pragma\", \"no-cache\")";
        ((JavascriptExecutor) webDriver).executeScript(clearCacheJavascript);
        webDriver.navigate().refresh();
    }
}
