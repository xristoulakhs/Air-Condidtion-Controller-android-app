package com.example.air_condition.dao;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    static List<AC> acList = new ArrayList<>();
    static int counter = 0;

    static {
        acList.add(new AC("Living room", 25, false, "Low", "Down", "Cold", false, false, false));
        acList.add(new AC("Bedroom", 23, false, "Low", "Down", "Cold", false, false, false));
        acList.add(new AC("Kitchen", 27, false, "Low", "Down", "Cold", false, false, false));
    }


    public static AC getNextAC(){
        counter = (counter + 1) % acList.size();
        return acList.get(counter);
    }

    public static AC getPrevious(){
        counter = (counter - 1 + acList.size()) % acList.size();
        return acList.get(counter);
    }

    public static AC getCurrent() {
        return acList.get(counter);
    }
}
