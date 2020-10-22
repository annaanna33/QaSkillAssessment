package com.omniupdate.addressbook.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.omniupdate.addressbook.pojo.ContactPOJO;
import com.omniupdate.addressbook.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class API_Testing {

    static ContactPOJO contact;
    static Faker faker;
    static RequestSpecification requestSpec ;


    @BeforeClass
    public static void init() {
        faker = new Faker();
        baseURI = ConfigurationReader.getProperty("baseURI");
        requestSpec = given().accept(ContentType.JSON).and().queryParam("apikey", ConfigurationReader.getProperty("apikey")).log().all();
    }

    @Test(priority = 1, testName = "Adding new Contact", groups = {"api"})
    public void testAddContact() throws JsonProcessingException {
        contact = new ContactPOJO();
        contact.setFirstName(faker.name().firstName());
        contact.setLastName(faker.name().lastName());
        contact.setEmail(faker.internet().emailAddress());
        contact.setPhone(String.valueOf(faker.number().randomNumber(10, true)));
        contact.setAddress(faker.address().streetAddress());
        contact.setCity(faker.address().cityName());
        contact.setState(faker.address().state());
        contact.setZipcode(faker.address().zipCode().substring(0, 5));

        //api is configured to have apikey in body
        contact.setApikey(ConfigurationReader.getProperty("apikey"));

        Response response =

                given().
                        spec(requestSpec).
                        body(contact).
                when().
                        post(ConfigurationReader.getProperty("createOne")).prettyPeek();

        //store generated
        contact.setId(response.path("id"));

        //assertion starts
        assertThat(response.getStatusCode(), is(200));

        //asserting returned payload against posted
        ObjectMapper obj = new ObjectMapper();
        ContactPOJO responseObj = obj.readValue(response.asString(), ContactPOJO.class);
        assertThat(responseObj, samePropertyValuesAs(contact, "apikey"));


    }

    @Test(priority = 2, testName = "Getting created contact with generated id", groups = {"api"})
    public void testReadData() throws JsonProcessingException {
        // used the ID that have been generated from previous request

        Response response =
                given().
                        spec(requestSpec).
                        queryParam("id", contact.getId()).
                when().
                        get(ConfigurationReader.getProperty("getOne")).prettyPeek();

        //assertion starts
        assertThat(response.getStatusCode(), is(200));

        //asserting returned payload against posted
        ObjectMapper obj = new ObjectMapper();
        ContactPOJO responseObj = obj.readValue(response.asString(), ContactPOJO.class);
        assertThat(responseObj, samePropertyValuesAs(contact, "apikey"));


    }

    @Test(priority = 3, testName = "Updating contact", groups = {"api"})
    public void testUpdateData() throws JsonProcessingException {
        //generating random data, and update contact
        contact.setFirstName(faker.name().firstName());
        contact.setLastName(faker.name().lastName());
        contact.setEmail(faker.internet().emailAddress());
        contact.setCity(faker.address().cityName());
        contact.setState(faker.address().state());

        Response response =
                given().
                    spec(requestSpec).
                    body(contact).
                when().
                    post(ConfigurationReader.getProperty("updateOne")).prettyPeek();

        //assertion starts
        assertThat(response.getStatusCode(), is(200));

        //assertion with converting json to java object, and checking values
        ObjectMapper obj = new ObjectMapper();
        ContactPOJO newUpdatedContact = obj.readValue(response.asString(), ContactPOJO.class);

        assertThat(newUpdatedContact, samePropertyValuesAs(contact, "apikey"));


    }

    @Test(priority = 4, testName = "Deleting contact", groups = {"api"})
    public void testDeleteData() {
        //delete generated data

                given().
                        spec(requestSpec).
                        body(contact).
                when().
                        post(ConfigurationReader.getProperty("deleteOne")).prettyPeek().
                then().
                        statusCode(200).
                        body("message", is("success"));

    }

    @Test(priority = 5, testName = "Negative Testing with invalid ID", groups = {"api"})
    public void  negativeTest(){

        //getting contact with invalid ID

        Response response =
                given().
                        spec(requestSpec).
                        queryParam("id", faker.idNumber().valid()).
                        when().
                        get(ConfigurationReader.getProperty("getOne")).prettyPeek();

        //assertion starts
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.path("error"), is("id not found"));

    }

}
