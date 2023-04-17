package com.example.air_condition;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.air_condition.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    LinearLayout fanBtn;
    LinearLayout directionBtn;
    LinearLayout modeBtn;
    LinearLayout ecoBtn;
    LinearLayout autoBtn;
    LinearLayout sleepBtn;

    LinearLayout powerBtn;

    TextView statusText;
    ImageView statusImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fanBtn = findViewById(R.id.fanButton);
        directionBtn = findViewById(R.id.directionButton);
        modeBtn = findViewById(R.id.modeButton);
        ecoBtn = findViewById(R.id.ecoButton);
        autoBtn = findViewById(R.id.autoButton);
        sleepBtn = findViewById(R.id.sleepButton);

        powerBtn = findViewById(R.id.onOffBtn);

        statusText = findViewById(R.id.statusText);
        statusImg = findViewById(R.id.statusIcon);

        fanBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: fffff");
            }
        });
    }
}