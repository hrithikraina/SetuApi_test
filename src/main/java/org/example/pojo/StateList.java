package org.example.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@Data
public class StateList
{
    private ArrayList<State>states;
    private int ttl;
}
