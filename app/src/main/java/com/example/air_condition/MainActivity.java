package com.example.air_condition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.air_condition.dao.AC;
import com.example.air_condition.dao.DAO;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

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

    int status = 0; //metabliti pou tha elegxei ean i suskeui einai anoixti, arxika 0
    //metablites gia leitourgies, elegxoun ean einai energopoiimenes, arxika 0
    int eco = 0;
    int auto = 0;
    int sleep =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                AC ac = DAO.getNextAC();
                currentText.setText(ac.getAc());
                setData(ac);
            }
        });

        nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AC ac = DAO.getPrevious();
                currentText.setText(ac.getAc());
                setData(ac);

            }
        });
        settingsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent =  new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        helpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpIntent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpIntent);
            }
        });

        plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                if(DAO.getCurrent().getDirectionOptions().equals("Down")){
                    Log.d("comment", "onClick: down");
                    directionOptions.setText("Middle");
                    DAO.getCurrent().setDirectionOptions("Middle");
                } else if (DAO.getCurrent().getDirectionOptions().equals("Middle")) {
                    Log.d("comment", "onClick: middle");
                    directionOptions.setText("Up");
                    DAO.getCurrent().setDirectionOptions("Up");
                }
                else {
                    Log.d("comment", "onClick: up");
                    directionOptions.setText("Down");
                    DAO.getCurrent().setDirectionOptions("Down");
                }
            }
        });

        modeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        temperatureText.setText(Integer.toString(ac.getTemperature()));

        if(DAO.getCurrent().getDirectionOptions().equals("Middle")){
            directionOptions.setText("Middle");
        } else if (DAO.getCurrent().getDirectionOptions().equals("Up")) {
            directionOptions.setText("Up");
        }
        else {
            directionOptions.setText("Down");
        }

    }
}