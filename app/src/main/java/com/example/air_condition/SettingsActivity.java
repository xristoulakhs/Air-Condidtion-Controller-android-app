package com.example.air_condition;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.air_condition.dao.DAO;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    TextView settingsTxt;

    Spinner scaleSpinner;
    Spinner devicesSpinner;
    Spinner languageSpinner;

    RadioButton rb_cel;
    RadioButton rb_fahr;

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

        rb_cel = findViewById(R.id.temperatureScaleCelsius);
        rb_cel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAO.setCelcius(true);
            }
        });

        rb_fahr = findViewById(R.id.temperatureScaleFahrenheit);
        rb_fahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAO.setCelcius(false);
            }
        });

        if(DAO.isCelcius()){
            rb_cel.setChecked(true);
        }
        else {
            rb_fahr.setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
