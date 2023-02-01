package org.example.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@NoArgsConstructor
@Getter
@Setter
@Data
public class HospitalList {
    private ArrayList<Session>sessions;
}
