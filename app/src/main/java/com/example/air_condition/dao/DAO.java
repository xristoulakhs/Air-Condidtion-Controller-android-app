package com.example.air_condition.dao;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    static List<AC> acList = new ArrayList<>();
    static int counter = 0;
    static boolean celcius = true; // an einai celcius

    static boolean turboState = false;
    static boolean vibrationState = false;
    static boolean speechState = false;

    static String timerText = "20 mins";

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


    public static boolean isCelcius() {
        return celcius;
    }

    public static void setCelcius(boolean celcius) {
        DAO.celcius = celcius;
    }

    public static boolean isTurboState() {
        return turboState;
    }

    public static void setTurboState(boolean turboStateF) {
        turboState = turboStateF;
    }

    public static boolean isVibrationState() {
        return vibrationState;
    }

    public static void setVibrationState(boolean vibrationStateF) {
        vibrationState = vibrationStateF;
    }

    public static boolean isSpeechState() {
        return speechState;
    }

    public static void setSpeechState(boolean speechStateF) {
        speechState = speechStateF;
    }


    public static String getTimerText() {
        return timerText;
    }

    public static void setTimerText(String timerText) {
        DAO.timerText = timerText;
    }
}
