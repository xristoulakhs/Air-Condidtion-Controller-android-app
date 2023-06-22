package com.example.air_condition;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.air_condition.dao.DAO;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    TextView timerTxt;

    ImageView timerPlus;
    ImageView timerMinus;

    Spinner scaleSpinner;
    Spinner devicesSpinner;
    Spinner languageSpinner;

    RadioButton rb_cel;
    RadioButton rb_fahr;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch turboSwitch;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch vibrationSwitch;
    Switch speechSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        timerTxt = findViewById(R.id.timerText);

        timerMinus = findViewById(R.id.timerMinus);
        timerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int txtNum = Integer.parseInt(DAO.getTimerText().split(" ",2)[0]);
                if(txtNum>0){
                    timerTxt.setText(txtNum - 5 + " mins");
                    DAO.setTimerText(txtNum - 5 +" mins");
                }
            }
        });

        timerPlus =findViewById(R.id.timerPlus);
        timerPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int txtNum = Integer.parseInt(DAO.getTimerText().split(" ",2)[0]);
                if(txtNum<100){
                    timerTxt.setText(txtNum + 5 + " mins");
                    DAO.setTimerText(txtNum + 5 +" mins");
                }
            }
        });

        devicesSpinner = findViewById(R.id.myDevicesSpinner);
        ArrayList<String> devices = new ArrayList<>();
        devices.add("Living Room");
        devices.add("Kitchen");
        devices.add("Bedroom");
        ArrayAdapter<String> devicesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, devices);
        devicesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        devicesSpinner.setAdapter(devicesArrayAdapter);

        turboSwitch = findViewById(R.id.turboSwitch);
        turboSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAO.setTurboState(!DAO.isTurboState());
            }
        });

        vibrationSwitch = findViewById(R.id.vibrationSwitch);
        vibrationSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAO.setVibrationState(!DAO.isVibrationState());
            }
        });

        speechSwitch = findViewById(R.id.voiceSwitch);
        speechSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAO.setSpeechState(!DAO.isSpeechState());
            }
        });

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

        turboSwitch.setChecked(DAO.isTurboState());
        vibrationSwitch.setChecked(DAO.isVibrationState());
        speechSwitch.setChecked(DAO.isSpeechState());
        timerTxt.setText(DAO.getTimerText());
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
