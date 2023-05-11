package com.almosafer.api;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeSuite
    protected void SetUp()
    {

    }

    @BeforeTest
    protected void setUpTest()
    {
        RestAssured.baseURI = "https://ae.almosafer.com";
    }

    @AfterSuite
    protected void tearDown()
    {

    }

    @AfterTest
    protected void afterTest()
    {

    }
}
