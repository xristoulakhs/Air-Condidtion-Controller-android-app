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
                //edw kai sto nextImg (koumpia pou allazoun tis suskeues) pairnoume plirofories
                //apo ton pinaka devices pou einai sto strings.xml ton opoio metatrepsame se arraylist
                //brikame to index tis suskeuis pou psaxnoume kai tin pirame me tin methodo get
                Context context = getApplicationContext();
                ArrayList<String> devicesArray = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.devices)));
                int previousDeviceIndex = devicesArray.indexOf(currentText.getText().toString()) - 1;
                if(previousDeviceIndex < 0){
                    currentText.setText(devicesArray.get(devicesArray.size() - 1));
                }
                else {
                    currentText.setText(devicesArray.get(previousDeviceIndex));
                }
            }
        });

        nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                ArrayList<String> devicesArray = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.devices)));
                int nextDeviceIndex = devicesArray.indexOf(currentText.getText().toString()) + 1;
                if(nextDeviceIndex >= devicesArray.size()){
                    currentText.setText(devicesArray.get(0));
                }
                else {
                    currentText.setText(devicesArray.get(nextDeviceIndex));
                }
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
                }
            }
        });

        minusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newTemperature = Integer.parseInt(temperatureText.getText().toString()) - 1;
                if(newTemperature>=15){
                    temperatureText.setText(Integer.toString(newTemperature));
                }
            }
        });

        powerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(status==0){
                   statusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_dot));
                   statusText.setText("On");
                   status=1;
               }
               else{
                   statusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_red_dot));
                   statusText.setText("Off");
                    status=0;
               }
            }
        });
        fanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fanOptions.getText().equals("Low")){
                    fanOptions.setText("Medium");
                } else if (fanOptions.getText().equals("Medium")) {
                    fanOptions.setText("High");
                }
                else {
                    fanOptions.setText("Low");
                }
            }
        });

        directionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(directionOptions.getText().equals("Down")){
                    directionOptions.setText("Middle");
                } else if (directionOptions.getText().equals("Middle")) {
                    directionOptions.setText("Up");
                }
                else {
                    directionOptions.setText("Down");
                }
            }
        });

        modeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(modeOptions.getText().equals("Cold")){
                    modeOptions.setText("Warm");
                    modeImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_warm));
                }
                else{
                    modeOptions.setText("Cold");
                    modeImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_cold));
                }
            }
        });

        ecoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eco==0){
                    ecoBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
                    eco = 1;
                }
                else {
                    ecoBtn.setBackground(getResources().getDrawable(R.drawable.button));
                    eco = 0;
                }
            }
        });

        autoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auto==0){
                    autoBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
                    auto = 1;
                }
                else {
                    autoBtn.setBackground(getResources().getDrawable(R.drawable.button));
                    auto = 0;
                }
            }
        });

        sleepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sleep==0){
                    sleepBtn.setBackground(getResources().getDrawable(R.drawable.button_on));
                    sleep = 1;
                }
                else {
                    sleepBtn.setBackground(getResources().getDrawable(R.drawable.button));
                    sleep = 0;
                }
            }
        });
    }
}