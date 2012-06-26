package org.test.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.util.HashMap;
import java.util.Map;

public class JZDrivers {

    public static Map<Integer, JZDriver> getJzDrivers() {
        return jzDrivers;
    }

    private static Map<Integer, JZDriver> jzDrivers = new HashMap<Integer, JZDriver>();

    private JZDrivers() {

    }

    public static void clearDrivers() {
        for (Map.Entry<Integer, JZDriver> jzDriverEntry : jzDrivers.entrySet()) {
            System.out.println("Closing down browser instance " + jzDriverEntry.getKey());
            jzDriverEntry.getValue().getWebDriver().quit();
        }
        jzDrivers.clear();
    }

    public static WebDriver getFirstDriver() throws Exception {
        if (jzDrivers.isEmpty()) createIfNotExists(1);
        return jzDrivers.get(1).getWebDriver();
    }

    public static JZDriver getJzDriverInstance(Integer i) throws Exception {
        try {
            createIfNotExists(i);
        } catch (UnreachableBrowserException e) {
            removeAndReplace(i);
        }
        return jzDrivers.get(i);
    }

    private static void createIfNotExists(Integer i) throws Exception {
        WebDriver driver;
        String logMessage;
        if (jzDrivers.get(i) == null) {
            logMessage = "The browser No." + i + " was null. Creating a new one";
            try {
                addWebDriver(i);
            } catch (UnreachableBrowserException e) {
                e.printStackTrace();
                removeAndReplace(i);
            }
        } else {
            logMessage = "Using existing browser instance " + i;
        }

        driver = jzDrivers.get(i).getWebDriver();

        if (driver.getTitle() == null || driver.getTitle().trim().equals("")) {
            logMessage += ". browser " + i + " isn't pointing into any sites.";
        } else {
            logMessage += ". browser " + i + " is pointing to: " + driver.getTitle();
        }
        System.out.println(logMessage);
    }

    private static JZDriver addWebDriver(Integer i) {
        JZDriver jzDriver = new JZDriver(new FirefoxDriver());
        jzDrivers.put(i, jzDriver);
        return jzDriver;
    }

    private static void removeAndReplace(Integer i) throws Exception {
        System.out.println("Removing browser No." + i + " and putting a new empty one in its place");
        jzDrivers.remove(i);
        addWebDriver(i);
    }

    public static JZDriver resetAndGet(Integer i) throws Exception {
        if (jzDrivers.get(i) == null) {
            System.out.println("RESET: The browser No." + i + " was null");
            createIfNotExists(i);
        }
        return jzDrivers.get(i);
    }
}
