package org.test.launch;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.test.web.JZDriver;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: kostasmamalis
 * Date: 27/03/2012
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                Google_launch.class
                //, Google_launch2.class
})
public class Main_Launch {

       @AfterClass
        public static void tearDown() {
            try {
                System.out.println("tearing down");
                closeAll();
                System.out.println("closed browsers gracefully...");
            } catch (Throwable throwable) {
                System.out.println("closing browsers wasn't done");
                throwable.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
       }

        protected static void closeAll() throws Throwable {
            Map<Integer, WebDriver> webDrivers = JZDriver.getWebDrivers();
            if (webDrivers != null && webDrivers.size() != 0){
                for(WebDriver webDriver : webDrivers.values()){
                     webDriver.quit();
                }
               JZDriver.clearWebDrivers();
            }
            try {
                JZDriver.getCurrentDriver().quit();
            } catch(NullPointerException npe){
                System.out.println("no browser was returned.");
            }
        }
}
