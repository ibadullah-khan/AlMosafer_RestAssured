package com.almosafer.api.hotel.test.GET;

import com.almosafer.api.BaseTest;
import com.almosafer.api.constants.Endpoints;
import com.almosafer.api.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class LookUps extends BaseTest {

    static RequestSpecification commonSpecs()
    {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath(Endpoints.HOTEL_LOOKUP);
        builder.addHeader("token", ConfigManager.getInstance().gettoken());

        RequestSpecification specification = builder.build();
        return specification;
    }

    @Test
    void getHotelsLookupAndVerify()
    {
        RestAssured.given()
                .when()
                .spec(commonSpecs())
                .get()
                .then()
                .log()
                .all()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body(is(notNullValue()));
    }


}
