import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.pojo.State;
import org.example.pojo.Session;
import org.example.pojo.District;
import org.example.pojo.DistrictList;
import org.example.pojo.SessionList;
import org.example.pojo.Slot;
import org.example.pojo.StateList;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class apiTests
{
    //Validity 1: Validate that the state id of karnataka is 16
    @Test
    void valid1()
    {
        String queryState="Karnataka";
        int queryStateId=16;

        Response res= RestAssured.get("https://cdn-api.co-vin.in/api/v2/admin/location/states");
        StateList slist= res.getBody().as(StateList.class);

int flag=0;
        for(State x : slist.states)
        {
            if (x.state_id==queryStateId)

                if (x.state_name.equals(queryState)) {

                     flag=1;
                }
        }
Assert.assertEquals(flag,1);


    }

    //Validity 2: Validate the district id of Bangalore Urban is "265"
    @Test
    void valid2()
    {
        String queryDistrict="Bangalore Urban";
        int queryDistrictId=265;

        Response res = RestAssured.get("https://cdn-api.co-vin.in/api/v2/admin/location/districts/16/");
        DistrictList dlist= res.getBody().as(DistrictList.class);

        int flag=0;
        for(District x : dlist.districts)
        {
            if (x.district_id==queryDistrictId)

                if (x.district_name.equals(queryDistrict)) {

                    flag=1;
                }
        }
        Assert.assertEquals(flag,1);
    }
    //3. Validate  that all states/UTs have their state_id
    @Test
    void valid3()
    {


        Response res= RestAssured.get("https://cdn-api.co-vin.in/api/v2/admin/location/states");
        StateList slist= res.getBody().as(StateList.class);

        int len= slist.states.size();
        int c=0;

        for(State x : slist.states)
        {
            if(x.state_id!=0)
            {
                c++;
            }
        }
        Assert.assertEquals(c,len);
    }

    //Validate the price of vaccine does in Hospital "Springleaf Healthcare [State :   Karnataka, District : Bangalore Urban] is > Rs 300
    @Test
    void valid4()
    {
        Response res= RestAssured.get("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=265&date=28-01-2023");
        SessionList slist= res.getBody().as(SessionList.class);
        String queryState="Karnataka";
        String queryDistrict="Bangalore Urban";
        String hospital= "Springleaf Healthcare";
        int fee_limit=300;
        int flag=0;
        for(Session s:slist.sessions)
        {
            if(Objects.equals(s.state_name, queryState) && Objects.equals(s.district_name, queryDistrict) && Objects.equals(s.name, hospital))
            {
                float price= Float.parseFloat(s.fee);
                if(price>fee_limit)
                {
                    flag=1;
                    break;
                }


            }
        }
        Assert.assertEquals(flag,1);

    }

    //Validate that atleast one Hospital is providing vaccine as Free
    @Test
    void valid5()
    {
        Response res= RestAssured.get("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=265&date=28-01-2023");
        SessionList slist= res.getBody().as(SessionList.class);
        String free_fee="Free";

        int flag=0;
        for(Session s:slist.sessions)
        {
            if(Objects.equals(s.fee_type, free_fee))
            {
               flag=1;
               break;

            }

        }
        Assert.assertEquals(flag,1);


    }
}
