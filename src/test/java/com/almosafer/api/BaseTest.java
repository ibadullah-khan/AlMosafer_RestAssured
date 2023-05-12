package com.almosafer.api;

import com.almosafer.api.utils.ConfigManager;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class BaseTest {
    @BeforeSuite
    protected void SetUp() {

    }

    @BeforeTest
    protected void setUpTest()
    {
        RestAssured.baseURI = ConfigManager.getInstance().getURL();
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
