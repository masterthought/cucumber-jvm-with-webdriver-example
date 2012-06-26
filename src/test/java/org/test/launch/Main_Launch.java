package org.test.launch;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.test.web.JZDrivers;

@RunWith(Suite.class)
@Suite.SuiteClasses({Google_launch.class})
public class Main_Launch {

    @AfterClass
    public static void tearDown() {
        try {
            System.out.println("tearing down");
            JZDrivers.clearDrivers();
            System.out.println("closed browsers gracefully...");
        } catch (Throwable throwable) {
            System.out.println("closing browsers wasn't done");
            throwable.printStackTrace();
        }
    }

}
