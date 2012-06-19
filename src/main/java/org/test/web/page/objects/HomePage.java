package org.test.web.page.objects;

public class HomePage {

    public HomePage(){ }

    public static HomePage getInstance() throws Exception {
      return new PageInstantiator<HomePage>(new HomePage()).getPageInstance();
    }


}
