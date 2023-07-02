package com.example.air_condition;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.air_condition.dao.DAO;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

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

    Vibrator vibrator;

    String[] languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (DAO.getLanguage().equals("el")) languages = new String[]{"Επιλέξτε Γλώσσα", "English", "Ελληνικά"};
        if (DAO.getLanguage().equals("en")) languages = new String[]{"Select Language", "English", "Ελληνικά"};
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        timerTxt = findViewById(R.id.timerText);

        timerMinus = findViewById(R.id.timerMinus);
        timerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                int txtNum = Integer.parseInt(DAO.getTimerText().split(" ",2)[0]);
                if(txtNum>0){
                    timerTxt.setText(txtNum - 5 + " ''");
                    DAO.setTimerText(txtNum - 5 +" ''");
                }
            }
        });

        timerPlus =findViewById(R.id.timerPlus);
        timerPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                int txtNum = Integer.parseInt(DAO.getTimerText().split(" ",2)[0]);
                if(txtNum<100){
                    timerTxt.setText(txtNum + 5 +" ''");
                    DAO.setTimerText(txtNum + 5 +" ''");
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
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setTurboState(!DAO.isTurboState());
            }
        });

        vibrationSwitch = findViewById(R.id.vibrationSwitch);
        vibrationSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setVibrationState(!DAO.isVibrationState());
            }
        });

        speechSwitch = findViewById(R.id.voiceSwitch);
        speechSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setSpeechState(!DAO.isSpeechState());
            }
        });

        rb_cel = findViewById(R.id.temperatureScaleCelsius);
        rb_cel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setCelcius(true);
            }
        });

        rb_fahr = findViewById(R.id.temperatureScaleFahrenheit);
        rb_fahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setCelcius(false);
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,languages);

        languageSpinner = findViewById(R.id.languageSpinner);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLanguage = adapterView.getItemAtPosition(i).toString();
                if(selectedLanguage.equals("English")){
                    setLocale("en",SettingsActivity.this);
                    DAO.setLanguage("en");
                    setResult(RESULT_OK);
                    finish();
                    startActivity(getIntent());
                } else if (selectedLanguage.equals("Ελληνικά")) {
                    setLocale("el",SettingsActivity.this);
                    DAO.setLanguage("el");
                    setResult(RESULT_OK);
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

    public void setLocale(String lang, Activity activity) {
        Locale myLocale = new Locale(lang);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(myLocale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }
}
