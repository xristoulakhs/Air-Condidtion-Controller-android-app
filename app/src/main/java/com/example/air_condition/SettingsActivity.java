package com.example.air_condition;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView;

import android.content.res.Resources;
import android.content.res.Configuration;
import java.util.Locale;


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

    ImageButton editDevicesButton;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        timerTxt = findViewById(R.id.timerText);

        timerMinus = findViewById(R.id.timerMinus);
        timerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DAO.isVibrationState()) vibrator.vibrate(100);
                int txtNum = Integer.parseInt(DAO.getTimerText().split(" ", 2)[0]);
                if (txtNum > 0) {
                    timerTxt.setText(txtNum - 5 + " mins");
                    DAO.setTimerText(txtNum - 5 + " mins");
                }
            }
        });

        timerPlus = findViewById(R.id.timerPlus);
        timerPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DAO.isVibrationState()) vibrator.vibrate(100);
                int txtNum = Integer.parseInt(DAO.getTimerText().split(" ", 2)[0]);
                if (txtNum < 100) {
                    timerTxt.setText(txtNum + 5 + " mins");
                    DAO.setTimerText(txtNum + 5 + " mins");
                }
            }
        });

        // Retrieve the languageSpinner from the layout
        languageSpinner = findViewById(R.id.languageSpinner);

        // Create an array of language options
        String[] languages = {"English", "Greek"};

        // Create an ArrayAdapter to populate the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter on the spinner
        languageSpinner.setAdapter(adapter);

        devicesSpinner = findViewById(R.id.myDevicesSpinner);
        ArrayList<String> devices = new ArrayList<>();
        devices.add("Living Room");
        devices.add("Kitchen");
        devices.add("Bedroom");
        ArrayAdapter<String> devicesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, devices);
        devicesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        devicesSpinner.setAdapter(devicesArrayAdapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedLanguage = adapterView.getItemAtPosition(position).toString();
                setAppLanguage(selectedLanguage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });



        turboSwitch = findViewById(R.id.turboSwitch);
        turboSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setTurboState(!DAO.isTurboState());
            }
        });

        vibrationSwitch = findViewById(R.id.vibrationSwitch);
        vibrationSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setVibrationState(!DAO.isVibrationState());
            }
        });

        speechSwitch = findViewById(R.id.voiceSwitch);
        speechSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setSpeechState(!DAO.isSpeechState());
            }
        });

        rb_cel = findViewById(R.id.temperatureScaleCelsius);
        rb_cel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setCelcius(true);
            }
        });

        rb_fahr = findViewById(R.id.temperatureScaleFahrenheit);
        rb_fahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DAO.isVibrationState()) vibrator.vibrate(100);
                DAO.setCelcius(false);
            }
        });

        if (DAO.isCelcius()) {
            rb_cel.setChecked(true);
        } else {
            rb_fahr.setChecked(true);
        }

        turboSwitch.setChecked(DAO.isTurboState());
        vibrationSwitch.setChecked(DAO.isVibrationState());
        speechSwitch.setChecked(DAO.isSpeechState());
        timerTxt.setText(DAO.getTimerText());

        editDevicesButton = findViewById(R.id.editDevicesButton);
        editDevicesButton.setVisibility(View.GONE);

        devicesSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Show the "Edit Devices" button when the spinner is touched
                editDevicesButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        editDevicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event of the "Edit Devices" button
                // Add your logic here
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }


    private void setAppLanguage(String language) {
        // Get the application's resources
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();

        // Create a locale object based on the selected language
        Locale locale = new Locale(language);

        // Update the configuration with the new locale
        configuration.setLocale(locale);

        // Update the resources with the new configuration
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        // Restart the activity to apply the language change
        recreate();
    }


}
