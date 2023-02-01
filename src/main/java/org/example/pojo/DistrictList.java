package org.example.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@Data
public class DistrictList
{
    private ArrayList<District>districts;
    private int ttl;
}
