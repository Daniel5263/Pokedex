package com.example.pokedex;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    public AdapterView.OnItemSelectedListener spinner1 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    public void saveEvent(View v) {

            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        }
        public void resetEvent(View v) {

        Toast.makeText(this, "Reset", Toast.LENGTH_LONG).show();
        }

        Spinner spinner_id;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_id = findViewById(R.id.spinner_id);

        spinner_id.setOnItemSelectedListener(spinner1);

        String[] list = new String[50];
        for(int i=0;i<50;i++)
        {
            list[i] = String.valueOf(i+1);
        }

        ArrayAdapter <String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_id.setAdapter(ad);
        }

}