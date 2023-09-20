package com.example.pokedex;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void saveEvent() {

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    public void resetEvent(View v) {

        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
    }

    Spinner spinner_id;
    EditText height_input;
    EditText name_input;
    EditText national_input;
    Button save_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height_input = findViewById(R.id.height_input);
        name_input = findViewById(R.id.name_input);
        national_input = findViewById(R.id.national_input);
        save_id = findViewById(R.id.save_id);

        save_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputHeight = height_input.getText().toString();
                String inputName = name_input.getText().toString();
                int inputNat = Integer.parseInt(String.valueOf(national_input.getText()));

                //Validating National Number
                if(isValidNat(inputNat)) {
                    national_input.setTextColor(Color.BLACK);
                } else {
                    national_input.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Invalid National Number", Toast.LENGTH_SHORT).show();
                }

                //Validating Name
                if(isValidName(inputName)) {
                    name_input.setTextColor(Color.BLACK);
                } else {
                    name_input.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                }

                //Validating Height
                if(isValidHeight(inputHeight)) {
                    height_input.setTextColor(Color.BLACK);
                } else {
                    height_input.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Invalid Height", Toast.LENGTH_SHORT).show();
                }

                //Save event if all fields are valid
                if(isValidName(inputName) && isValidHeight(inputHeight) && isValidNat(inputNat)) {
                    saveEvent();
                }
            }

            public boolean isValidNat(int inputNat) {
                return inputNat >= 0 && inputNat <= 1010;
            }

            public boolean isValidName(String inputName) {
                String pattern = "^[a-zA-Z. ]*$";
                return inputName.matches(pattern);
            }

            public boolean isValidHeight(String heightInput) {
                try {
                    Double.parseDouble(heightInput);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });


        //List 1-50 for Level
        spinner_id = findViewById(R.id.spinner_id);

        String[] list = new String[50];
        for (int i = 0; i < 50; i++) {
            list[i] = String.valueOf(i + 1);
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_id.setAdapter(ad);
    }
}
