package org.example.constants;


public class Apis
{
    public final static String BASE_URL = "https://cdn-api.co-vin.in/api/v2";
    public final static String GET_STATES = "/admin/location/states";
    public final static String GET_DISTRICTS = "/admin/location/districts/{state_id}";
    public final static String GET_HOSPITALS = "/appointment/sessions/public/findByDistrict";
}

