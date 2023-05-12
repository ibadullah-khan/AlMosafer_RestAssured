package com.almosafer.api.flights.test.POST;

import com.almosafer.api.BaseTest;
import com.almosafer.api.constants.Endpoints;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Epic("AlMosafer API testing using TestNG and Rest Assured")
@Feature("Verifying Flight Fare Module")

public class Fares extends BaseTest {

    static ResponseSpecification commonResponseSpecs()
    {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        ResponseSpecification specification = builder.build();
        return specification;
    }


    @Test(description = "Check flight details returned after post call")
    @Story("Get flights for the next days")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify that data is returned for the flight")
    void getFaresCalenderAndVerify()
    {
        RestAssured.basePath = Endpoints.GET_FARES_CALENDER;

        String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date
        c.add(Calendar.DATE, 2); // Adding 2 days
        String todaysAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

        JSONArray legs = new JSONArray();

        JSONObject leg1 = new JSONObject();
        leg1.put("originId","RUH");
        leg1.put("destinationId","DXB");
        leg1.put("departureFrom",today);
        leg1.put("departureTo",todaysAfter);

        JSONObject leg2 = new JSONObject();
        leg2.put("originId","DXB");
        leg2.put("destinationId","RUH");
        leg2.put("departureFrom",today);
        leg2.put("departureTo",todaysAfter);

        legs.put(0,leg1);
        legs.put(1,leg2);

        JSONObject pax = new JSONObject();
        pax.put("adult",1);
        pax.put("child",0);
        pax.put("infant",0);

        JSONObject data = new JSONObject();
        data.put("airports",new JSONObject());
        data.put("timeSlots",new JSONObject());
        data.put("stops",new JSONArray());
        data.put("airline",new JSONArray());
        data.put("cabin","Economy");
        data.put("pax",pax);
        data.put("leg",legs);

        given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                .when()
                .post()
                .then()
                .log()
                .all()
                .spec(commonResponseSpecs())
                .statusLine("HTTP/1.1 200 OK")
                .body(is(notNullValue()));

    }
}
