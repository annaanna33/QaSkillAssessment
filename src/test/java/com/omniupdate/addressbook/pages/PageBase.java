package com.omniupdate.addressbook.pages;

import com.omniupdate.addressbook.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase {
    public PageBase() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
