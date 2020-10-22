package com.omniupdate.addressbook.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {

    @FindBy(xpath = "//button[@class='add-contact-btn float-btn']")
    public WebElement addButton;

    @FindBy (css = "button.cancel-btn")
    public WebElement cancelButton;

    @FindBy (css = "button.save-btn")
    public WebElement saveButton;

    @FindBy (name = "firstName")
    public WebElement formFirstName;

    @FindBy (xpath = "//input[@placeholder='Last Name']")
    public WebElement formLastName;

    @FindBy (name = "phone")
    public WebElement formPhone;

    @FindBy (name = "email")
    public WebElement formEmail;

    @FindBy (xpath = "//a[text()='More fields']")
    public WebElement moreField;

    @FindBy (xpath = "//input[@placeholder='Address']")
    public WebElement formAddress;

    @FindBy (xpath = "//input[@placeholder='City']")
    public WebElement formCity;

    @FindBy (xpath = "//input[@placeholder='State']")
    public WebElement formState;

    @FindBy (xpath = "//input[@placeholder='Zipcode']")
    public WebElement formZipcode;

    @FindBy (xpath = "//i[.='select_all']")
    public WebElement selectContacts;

    @FindBy (xpath = "//*[@placeholder='Search']")
    public WebElement search;

    @FindBy (xpath = "//input[@ng-hide='vm.multiSelect']")
    public WebElement filter;

    @FindBy (xpath = "//div[@class='sort-btn navbar-btn']")
    public WebElement sort;

    @FindBy (css = "div.back-btn")
    public WebElement hideButton;

    @FindBy (xpath = "//*[text()='Confirm']")
    public WebElement confirmDelete;

    @FindBy (xpath = "//input[@ng-model='vm.contact.phone']")
    public WebElement editPhoneForm;

    @FindBy (xpath = "//input[@ng-model='vm.contact.email']")
    public WebElement editEmailForm;

    @FindBy (xpath = "//input[@ng-model='vm.contact.lastName']")
    public WebElement editLastNameForm;

    @FindBy (css = "button.save-btn")
    public WebElement updateContact;

    @FindBy (xpath = "//div[@class='contact-email ng-binding ng-hide']")
    public WebElement getSingleEmail;

    @FindBy (xpath = "//div[@class='contact-name ng-binding']")
    public WebElement getAllNames;









}
