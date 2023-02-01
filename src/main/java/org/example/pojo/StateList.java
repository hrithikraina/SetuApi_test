package org.example.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@Data
public class StateList
{
    private ArrayList<State>states;
    private int ttl;
}
