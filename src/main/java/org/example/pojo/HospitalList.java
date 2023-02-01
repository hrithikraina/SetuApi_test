package org.example.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@Data
public class HospitalList {
    private ArrayList<Session>sessions;
}
