package com.example.air_condition;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;


public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        //click listener for the back arrow
        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event, e.g., navigate to the main screen
                Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


                TextView textHelp = findViewById(R.id.textHelp);



        // Further customization of the TextView can be done here

            }
        });
    }
}
