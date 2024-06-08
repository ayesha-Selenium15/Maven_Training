package com.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HttpGetRequestTest {

    @Test
    public void getRequestTest(){
        Response response = RestAssured.given()
                .baseUri("https://dummy.restapiexample.com")
                .basePath("/api/v1/employees")
                .when()
                .get();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.jsonPath().get("status").toString());
        System.out.println(response.jsonPath().get("message").toString());
    }

    @Test
    public void postRequestTest(){
        Response response = RestAssured.given()
                .baseUri("https://dummy.restapiexample.com")
                .basePath("/api/v1/create")
                .when()
                .post();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.jsonPath().get("status").toString());
        System.out.println(response.jsonPath().get("data.id").toString());
        System.out.println(response.jsonPath().get("message").toString());

        Assert.assertEquals(response.jsonPath().get("status").toString(),"success");
        Assert.assertNotNull(response.jsonPath().get("data.id").toString()); // null
        Assert.assertEquals(response.jsonPath().get("message").toString(),"Successfully! Record has been added.");
    }

    @Test
    public void putRequestTest(){
        Response response = RestAssured.given()
                .baseUri("https://dummy.restapiexample.com")
                .basePath("/api/v1/update/9666")
                .when()
                .put();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.jsonPath().get("status").toString());
        System.out.println(response.jsonPath().get("data.id").toString());
        System.out.println(response.jsonPath().get("message").toString());

        Assert.assertEquals(response.jsonPath().get("status").toString(),"success");
        Assert.assertNotNull(response.jsonPath().get("data.id").toString()); // null
        Assert.assertEquals(response.jsonPath().get("message").toString(),"Successfully! Record has been updated.");
    }

    @Test
    public void deleteRequestTest(){
        Response response = RestAssured.given()
                .baseUri("https://dummy.restapiexample.com")
                .basePath("/api/v1/delete/5019")
                .when()
                .delete();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.jsonPath().get("status").toString());
        System.out.println(response.jsonPath().get("data").toString());
        System.out.println(response.jsonPath().get("message").toString());

        Assert.assertEquals(response.jsonPath().get("status").toString(),"success");
        Assert.assertNotNull(response.jsonPath().get("data").toString()); // null
        Assert.assertEquals(response.jsonPath().get("message").toString(),"Successfully! Record has been deleted");
    }

    @Test
    public void postRequestTest1() throws FileNotFoundException {

       /* String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";*/
        File file = new File(System.getProperty("user.dir") + "\\postRequest.json");

        // Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);

        System.out.println(file.toString());
        Response response = RestAssured.given()
                .baseUri("https://resreq.in")
                .basePath("/api/users")
                .contentType("application/json")
                .when()
                .body(inputStream.toString()).log().all()
                .post();

        System.out.println(response.statusCode());
    }
}
