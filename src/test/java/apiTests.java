import org.example.apiresponse.ApiSetuResponse;
import org.example.pojo.District;
import org.example.pojo.Session;
import org.example.pojo.State;
import org.example.pojo.StateList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

public class apiTests {
    private ApiSetuResponse apiSetuResponse;
    private StateList stateResponse;
    private String date;

    @BeforeClass
    public void init() {
        apiSetuResponse = new ApiSetuResponse();
        stateResponse = apiSetuResponse.getStateResponse();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(new Date());
    }

    //1) Validate the state id of Karnataka is "16".
    @Test
    public void validateStateId() {
        int stateId = 16;
        String stateName = "Karnataka";
        boolean checkStateId = false;
        for (State state : stateResponse.getStates()) {
            if (Objects.equals(state.getState_name(), stateName) && Objects.equals(state.getState_id(), stateId)) {
                checkStateId = true;
                break;
            }
        }
        assertTrue(checkStateId, "State ID of Karnataka is not 16");
    }

    //2) Validates that the district ID of Bangalore Urban is 265
    @Test
    public void validateDistrictId() {
        ArrayList<District> districts = apiSetuResponse.getDistrictResponse(16).getDistricts();
        int districtId = 265;
        boolean checkDistrictId = false;
        String districtName = "Bangalore Urban";
        for (District district : districts) {
            if (Objects.equals(district.getDistrict_name(), districtName) && Objects.equals(district.getDistrict_id(), districtId)) {
                checkDistrictId = true;
                break;
            }
        }
        assertTrue(checkDistrictId, "District ID of Bangalore Urban is not 265");
    }

    //3) Validate all states have their ids
    @Test
    public void validateAllStateHaveIds() {
        ArrayList<State> states = stateResponse.getStates();
        boolean checkIds = true;
        for (State state : states) {
            if (state.getState_id() == 0) {
                checkIds = false;
                break;
            }
        }
        assertTrue(checkIds, "All states doesn't have their IDs");
    }


    //4) Validates that Springleaf Healthcare's vaccine fees > Rs 300
    @Test
    public void validateVaccineFee() {
        ArrayList<Session> sessions = apiSetuResponse.getHospitals(265, date).getSessions();
        float limit = 300;
        String hospitalName = "Springleaf Healthcare";
        boolean checkPrice = false;
        for (Session session : sessions) {
            if (Objects.equals(session.getName(), hospitalName)) {
                float fee = Float.parseFloat(session.getFee());
                if (fee >= limit) {
                    checkPrice = true;
                    break;
                }
            }
        }
        Assert.assertTrue(checkPrice, "Springleaf Healthcare's vaccine have price less than 300.");
    }

    //5) Validates that there exists at least 1 free vaccine center
    @Test
    public void validateanyHospitalWithFreeVaccine() {
        ArrayList<Session> sessions = apiSetuResponse.getHospitals(265, date).getSessions();
        boolean freeVaccine = false;
        for (Session session : sessions) {
            if (Objects.equals(session.getFee_type(), "Free")) {
                freeVaccine = true;
                break;
            }
        }
        Assert.assertTrue(freeVaccine, "Free vaccine is not available in any hospital");
    }
}
