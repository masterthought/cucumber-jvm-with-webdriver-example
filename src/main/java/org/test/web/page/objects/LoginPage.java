package org.test.web.page.objects;

import org.test.web.JZDrivers;

public class LoginPage{

    public LoginPage(){
    }

    public static LoginPage getInstance() throws Exception {
         return new PageInstantiator<LoginPage>(new LoginPage()).getPageInstance();
    }

    public static void main(String[] args) throws Exception {
        LoginPage loginPage = LoginPage.getInstance();

        System.out.println(loginPage.getClass().toString());
        if(JZDrivers.getFirstDriver() != null){
            JZDrivers.getFirstDriver().quit();
        }

        HomePage homePage = HomePage.getInstance();
        System.out.println(homePage.getClass().toString());
        if(JZDrivers.getFirstDriver() != null){
            JZDrivers.getFirstDriver().quit();
        }
    }

}
