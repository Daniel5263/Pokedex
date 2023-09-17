package com.example.pokedex;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

        //List 1-50 for Level
        spinner_id = findViewById(R.id.spinner_id);

        String[] list = new String[50];
        for (int i = 0; i < 50; i++) {
            list[i] = String.valueOf(i + 1);
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_id.setAdapter(ad);

        //Integer between 0-1010 for National Number
        EditText national_input = findViewById(R.id.national_input);


        national_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    int nat_inp = Integer.parseInt(s.toString());
                    if (nat_inp < 0 || nat_inp > 1010) {
                        // Input is outside the valid range, turn text color red
                        national_input.setTextColor(Color.RED);
                    } else {
                        // Input is within the valid range, reset text color
                        national_input.setTextColor(Color.BLACK);
                    }
                }
            }
        });


    }
}
