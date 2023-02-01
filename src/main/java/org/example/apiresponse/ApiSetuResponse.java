package org.example.apiresponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.constants.Apis;
import org.example.pojo.DistrictList;
import org.example.pojo.HospitalList;
import org.example.pojo.StateList;

import static org.testng.Assert.assertEquals;

public class ApiSetuResponse {
    public ApiSetuResponse() {
        RestAssured.baseURI = Apis.BASE_URL;

    }

    public StateList getStateResponse() {
        RequestSpecification r = RestAssured.given();
        Response res = r.get(Apis.GET_STATES);
        StateList stateResponse = res.getBody().as(StateList.class);
        assertEquals(res.getStatusCode(), 200, "OK-States response received");
        return stateResponse;
    }

    public DistrictList getDistrictResponse(int stateID) {
        RequestSpecification r = RestAssured.given().pathParam("state_id", stateID);
        Response res = r.get(Apis.GET_DISTRICTS);
        DistrictList districtResponse = res.getBody().as(DistrictList.class);
        assertEquals(res.getStatusCode(), 200, "OK-Districts response received");
        return districtResponse;
    }

    public HospitalList getHospitals(int districtID, String date) {
        RequestSpecification r = RestAssured.given().queryParam("district_id", districtID).queryParam("date", date);
        Response res = r.get(Apis.GET_HOSPITALS);
        HospitalList covidVaccineCenterResponse = res.getBody().as(HospitalList.class);
        assertEquals(res.getStatusCode(), 200, "OK-Hospitals response received");
        return covidVaccineCenterResponse;
    }
}
