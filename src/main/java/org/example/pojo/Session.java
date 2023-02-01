package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@Data
public class Session{
    private int center_id;
    private String name;
    private String address;
    private String state_name;
    private String district_name;
    private String block_name;
    private int pincode;
    private String from;
    @JsonProperty("to")
    private String to;
    private int lat;
   @JsonProperty("long")
    private int mylong;
    private String fee_type;
    private String session_id;
    private String date;
    private int available_capacity;
    private int available_capacity_dose1;
    private int available_capacity_dose2;
    private String fee;
    private boolean allow_all_age;
    private int min_age_limit;
    private int max_age_limit;
    private String vaccine;
    private ArrayList<Slot> slots;
}