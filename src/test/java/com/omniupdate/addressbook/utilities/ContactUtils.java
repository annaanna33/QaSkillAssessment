package com.omniupdate.addressbook.utilities;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactUtils {

    //methods to create/search/edit contacts

    /**
     * Generate contact using Java Faker
     *
     * @return map
     */
    public static Map<String, String> createContact() {
        //Java faker for generating random data
        Faker faker = new Faker();
        //will add generated data as key and value
        Map<String, String> contact = new HashMap<>();

        String fullName = faker.name().firstName() + " " + faker.name().lastName();
        String firstName = fullName.substring(0, fullName.indexOf(" "));
        String lastName = fullName.substring(fullName.indexOf(" ") + 1);
        String phone = String.valueOf(faker.number().randomNumber(10, true));
        String email = faker.internet().emailAddress();
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode().substring(0, 5);

        contact.put("fullName", fullName);
        contact.put("firstName", firstName);
        contact.put("lastName", lastName);
        contact.put("phone", phone);
        contact.put("email", email);
        contact.put("address", address);
        contact.put("city", city);
        contact.put("state", state);
        contact.put("zipCode", zipCode);

        return contact;
    }

    /**
     * Edit contact by given name
     *
     * @param name
     */
    public static void editContactByName(String name) {
        Driver.getDriver().findElement(By.xpath("//div[.='" + name + "']/../following-sibling::div[3]")).click();
        Driver.getDriver().findElement(By.xpath("//div[.='" + name + "']/../following-sibling::div[3]/div[3]")).click();
    }

    /**
     * Delete contact by given name
     *
     *
     * @param name
     */
    public static void removeContactByName(String name) {
        Driver.getDriver().findElement(By.xpath("//div[.='" + name + "']/../following-sibling::div[3]")).click();
        Driver.getDriver().findElement(By.xpath("//div[.='" + name + "']/../following-sibling::div[3]/div[2]")).click();
    }

    /**
     * searching contact by full name
     * @param givenName
     * @return String
     */
    public static String searchContactByFullName(String givenName) {
        //add all names to List
        List<WebElement> allContacts = Driver.getDriver().findElements(By.xpath("//div[@class='contact-name ng-binding']"));

        //storing matching name in String
        String searchResult = "";

        //add search result to map for assertion
        for (WebElement eachContact : allContacts) {
            String getName = eachContact.getText();
            if (getName.equals(givenName)) {
                searchResult = getName;
            }
        }
        return searchResult;
    }


}
