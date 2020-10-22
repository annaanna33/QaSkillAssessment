package com.omniupdate.addressbook.test;

import com.omniupdate.addressbook.pages.HomePage;
import com.omniupdate.addressbook.utilities.BrowserUtils;
import com.omniupdate.addressbook.utilities.ConfigurationReader;
import com.omniupdate.addressbook.utilities.ContactUtils;
import com.omniupdate.addressbook.utilities.Driver;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;


public class UI_Testing {

    Map<String, String> contact;

    @BeforeClass
    public void setUp() {
        //store created contact in  map
        contact = ContactUtils.createContact();

        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        Driver.getDriver().manage().window().maximize();

    }

    //creating instance of POM class
    HomePage hp = new HomePage();

    @Test(testName = "Creating Contact", priority = 0, groups = {"ui"})
    public void e2eUI_create() {

        //synch issue, applied explicit wait
        BrowserUtils.waitForClickability(hp.addButton, 5).click();
        BrowserUtils.waitForVisibility(hp.formFirstName, 5).sendKeys(contact.get("firstName"));
        hp.formLastName.sendKeys(contact.get("lastName"));
        hp.formPhone.sendKeys(contact.get("phone"));
        hp.formEmail.sendKeys(contact.get("email"));

        hp.moreField.click();

        //synch issue, applied explicit wait
        BrowserUtils.waitForVisibility(hp.formAddress, 5).sendKeys(contact.get("address"));
        hp.formCity.sendKeys(contact.get("city"));
        hp.formState.sendKeys(contact.get("state"));
        hp.formZipcode.sendKeys(contact.get("zipCode"));

        hp.saveButton.click();
        //synch issue, applied explicit wait
        BrowserUtils.waitForClickability(hp.hideButton, 5).click();

        //assertion begins
        //storing actual result in String for comparison
        String actual = ContactUtils.searchContactByFullName(contact.get("fullName"));
        Assert.assertEquals(actual, contact.get("fullName"));

    }

    @Test(testName = "Editing Contact", priority = 1, groups = {"ui"})
    public void e2eUI_edit() {

        BrowserUtils.waitForVisibility(hp.search, 5).sendKeys(contact.get("firstName"));
        ContactUtils.editContactByName(contact.get("fullName"));

        //synch
        BrowserUtils.waitForVisibility(hp.editEmailForm, 5).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        hp.updateContact.click();
        BrowserUtils.waitForVisibility(hp.hideButton, 5).click();

        //assertion
        hp.search.sendKeys(Keys.ENTER);
        String actualEmail = hp.getSingleEmail.getText();
        Assert.assertTrue(actualEmail.isEmpty());


    }


    @Test(testName = "Removing Contact", priority = 2, groups = {"ui"})
    public void e2eUI_remove() throws InterruptedException {
        ContactUtils.removeContactByName(contact.get("fullName"));
        hp.confirmDelete.click();

        //assertion
        hp.search.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

        //after pressing delete button, it takes 1-2 second to delete it
        BrowserUtils.wait(2);

        String actualName = ContactUtils.searchContactByFullName(contact.get("fullName"));
        Assert.assertTrue(actualName.isEmpty());

    }
}
