package org.test.web.page.objects;

import org.test.web.JZDriver;

import static org.openqa.selenium.support.PageFactory.initElements;

public class PageInstantiator<T> {
    private T t;

    public PageInstantiator(T t){
         this.t = t;
    }

    public <T> T getPageInstance() throws Exception {
        return (T) initElements(JZDriver.getCurrentDriver(), this.t.getClass());
    }
}
