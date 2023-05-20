package com.example.air_condition;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    TextView settingsTxt;

    Spinner scaleSpinner;
    Spinner devicesSpinner;
    Spinner languageSpinner;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch turboSwitch;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch vibrationSwitch;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch timerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsTxt = findViewById(R.id.settingsTitle);
        settingsTxt.setPaintFlags(settingsTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); //gia upogrammisi tou text

        scaleSpinner = findViewById(R.id.temperatureScaleSpinner);

        devicesSpinner = findViewById(R.id.myDevicesSpinner);
        ArrayList<String> devices = new ArrayList<>();
        devices.add("Living Room");
        devices.add("Kitchen");
        devices.add("Bedroom");
        ArrayAdapter<String> devicesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, devices);
        devicesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        devicesSpinner.setAdapter(devicesArrayAdapter);

        turboSwitch = findViewById(R.id.turboSwitch);
        boolean turboState = turboSwitch.isChecked();

        vibrationSwitch = findViewById(R.id.vibrationSwitch);
        boolean vibrationState = turboSwitch.isChecked();

        timerSwitch = findViewById(R.id.timerSwitch);
        boolean timerState = turboSwitch.isChecked();
    }
}
