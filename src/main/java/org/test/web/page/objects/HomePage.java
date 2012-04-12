package org.test.web.page.objects;

/**
 * Created by IntelliJ IDEA.
 * User: kostasmamalis
 * Date: 02/04/2012
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class HomePage {

    public HomePage(){ }

    public static HomePage getInstance() throws Exception {
      return new PageInstantiator<HomePage>(new HomePage()).getPageInstance();
    }


}
