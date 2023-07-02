package com.example.air_condition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.air_condition.dao.AC;
import com.example.air_condition.dao.DAO;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int SETTINGS_REQUEST_CODE = 1;
    LinearLayout fanBtn;
    LinearLayout directionBtn;
    LinearLayout modeBtn;
    LinearLayout ecoBtn;
    LinearLayout autoBtn;
    LinearLayout sleepBtn;

    LinearLayout powerBtn;

    TextView statusText;
    TextView temperatureText;
    TextView currentText;
    TextView scaleText;

    TextView fanOptions;
    TextView directionOptions;
    TextView modeOptions;


    ImageView previousImg;
    ImageView nextImg;
    ImageView statusImg;
    ImageView settingsImg;
    ImageView helpImg;
    ImageView plusImg;
    ImageView minusImg;
    ImageView modeImg;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        previousImg = findViewById(R.id.previousDevice);
        currentText =findViewById(R.id.currentDevice);
        nextImg = findViewById(R.id.nextDevice);

        fanBtn = findViewById(R.id.fanButton);
        directionBtn = findViewById(R.id.directionButton);
        modeBtn = findViewById(R.id.modeButton);
        ecoBtn = findViewById(R.id.ecoButton);
        autoBtn = findViewById(R.id.autoButton);
        sleepBtn = findViewById(R.id.sleepButton);

        powerBtn = findViewById(R.id.onOffBtn);

        statusText = findViewById(R.id.statusText);
        scaleText = findViewById(R.id.scale);
        temperatureText = findViewById(R.id.temperature);

        fanOptions = findViewById(R.id.fanOptions);
        directionOptions = findViewById(R.id.directionOptions);
        modeOptions = findViewById(R.id.modeOptions);

        statusImg = findViewById(R.id.statusIcon);
        settingsImg = findViewById(R.id.homeSettingsIcon);
        helpImg =  findViewById(R.id.homeHelpIcon);
        plusImg = findViewById(R.id.plusImg);
        minusImg =  findViewById(R.id.minusImg);
        modeImg = findViewById(R.id.modeImage);

        previousImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                AC ac = DAO.getNextAC();
                currentText.setText(ac.getAc());
                setData(ac);
            }
        });

        nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                AC ac = DAO.getPrevious();
                currentText.setText(ac.getAc());
                setData(ac);

            }
        });
        settingsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
            }
        });



        helpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                Intent helpIntent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpIntent);
            }
        });

        plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                int newTemperature = Integer.parseInt(temperatureText.getText().toString()) + 1;
                if(newTemperature<=30){
                    temperatureText.setText(Integer.toString(newTemperature));
                    DAO.getCurrent().setTemperature(newTemperature);
                }
            }
        });

        minusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                int newTemperature = Integer.parseInt(temperatureText.getText().toString()) - 1;
                if(newTemperature>=15){
                    temperatureText.setText(Integer.toString(newTemperature));
                    DAO.getCurrent().setTemperature(newTemperature);
                }
            }
        });

        powerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                if(DAO.getCurrent().isStatus()){
                   statusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_red_dot));
                   statusText.setText("Off");
                   DAO.getCurrent().setStatus();
                   System.out.println(DAO.getCurrent());
                }
                else {
                   statusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_dot));
                   statusText.setText("On");
                   DAO.getCurrent().setStatus();
                   System.out.println(DAO.getCurrent());
                }
            }
        });
        fanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                if(DAO.getCurrent().getFanOption().equals("Low")){
                    fanOptions.setText("Medium");
                    DAO.getCurrent().setFanOption("Medium");
                } else if (DAO.getCurrent().getFanOption().equals("Medium")) {
                    fanOptions.setText("High");
                    DAO.getCurrent().setFanOption("High");
                }
                else {
                    fanOptions.setText("Low");
                    DAO.getCurrent().setFanOption("Low");
                }
            }
        });

        directionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                if(DAO.getCurrent().getDirectionOptions().equals("Down")){
                    directionOptions.setText("Middle");
                    DAO.getCurrent().setDirectionOptions("Middle");
                } else if (DAO.getCurrent().getDirectionOptions().equals("Middle")) {
                    directionOptions.setText("Up");
                    DAO.getCurrent().setDirectionOptions("Up");
                }
                else {
                    directionOptions.setText("Down");
                    DAO.getCurrent().setDirectionOptions("Down");
                }
            }
        });

        modeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                if(DAO.getCurrent().isModeOptions().equals("Cold")){
                    modeOptions.setText("Warm");
                    modeImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_warm));
                    DAO.getCurrent().setModeOptions("Warn");
                }
                else{
                    modeOptions.setText("Cold");
                    modeImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_cold));
                    DAO.getCurrent().setModeOptions("Cold");
                }
            }
        });

        ecoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                if(!DAO.getCurrent().isEco()){
                    ecoBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
                    DAO.getCurrent().setEco();
                }
                else {
                    ecoBtn.setBackground(getResources().getDrawable(R.drawable.button));
                    DAO.getCurrent().setEco();
                }
            }
        });

        autoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                if(!(DAO.getCurrent().isAuto())){
                    autoBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
                    DAO.getCurrent().setAuto();
                }
                else {
                    autoBtn.setBackground(getResources().getDrawable(R.drawable.button));
                    DAO.getCurrent().setAuto();
                }
            }
        });

        sleepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DAO.isVibrationState()) vibrator.vibrate(100);
                if(!DAO.getCurrent().isSleep()){
                    sleepBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
                    DAO.getCurrent().setSleep();
                }
                else {
                    sleepBtn.setBackground(getResources().getDrawable(R.drawable.button));
                    DAO.getCurrent().setSleep();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("f", DAO.getLanguage());
        Log.d("f", String.valueOf(resultCode));
        if (requestCode == SETTINGS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Call setData(DAO.getCurrent()) in MainActivity
                setData(DAO.getCurrent());
                if(DAO.getLanguage().equals("el")){
                    setLocale("el",MainActivity.this);
                    finish();
                    startActivity(getIntent());
                } else if (DAO.getLanguage().equals("en")) {
                    setLocale("en",MainActivity.this);
                    finish();
                    startActivity(getIntent());
                }
            }
        }
    }

    private void setData(AC ac){
        System.out.println(ac);
        if(ac.isStatus()){
            statusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_dot));
            statusText.setText("On");
        }
        else {
            statusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_red_dot));
            statusText.setText("Off");
        }

        if(ac.getFanOption().equals("Low")) fanOptions.setText("Low");
        else if (ac.getFanOption().equals("Medium")) fanOptions.setText("Medium");
        else fanOptions.setText("High");

        if(ac.isSleep()) sleepBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
        else sleepBtn.setBackground(getResources().getDrawable(R.drawable.button));

        if(ac.isAuto()) autoBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
        else autoBtn.setBackground(getResources().getDrawable(R.drawable.button));

        if(ac.isEco()) ecoBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
        else ecoBtn.setBackground(getResources().getDrawable(R.drawable.button));


        if(ac.isEco()) ecoBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
        else ecoBtn.setBackground(getResources().getDrawable(R.drawable.button));

        if(DAO.getCurrent().isModeOptions().equals("Cold")){
            modeOptions.setText("Cold");
            modeImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_cold));
        }
        else{
            modeOptions.setText("Warn");
            modeImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_warm));
        }

        if(DAO.getCurrent().equals("Up")){
            directionOptions.setText("Middle");
        } else if (DAO.getCurrent().equals("Middle")) {
            directionOptions.setText("Middle");
        }
        else {
            directionOptions.setText("Down");
        }

//        temperatureText.setText(Integer.toString(ac.getTemperature()));

        if(DAO.getCurrent().getDirectionOptions().equals("Middle")){
            directionOptions.setText("Middle");
        } else if (DAO.getCurrent().getDirectionOptions().equals("Up")) {
            directionOptions.setText("Up");
        }
        else {
            directionOptions.setText("Down");
        }

        if(DAO.isCelcius()){
            temperatureText.setText(Integer.toString(ac.getTemperature()));
            scaleText.setText("°C");
        }
        else{
            temperatureText.setText(Integer.toString(ac.getFahrenheit()));
            scaleText.setText("°F");
        }
    }

    public void setLocale(String lang, Activity activity) {
        Locale myLocale = new Locale(lang);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(myLocale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }
}