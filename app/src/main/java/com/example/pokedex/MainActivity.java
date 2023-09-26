package com.example.pokedex;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void saveEvent() {
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    public void resetEvent(View v) {
        height_input.setText("2.2 m");
        name_input.setText("Glastrier");
        national_input.setText("896");
        species_input.setText("Wild Horse Pokémon");
        weight_input.setText("800.0 kg");
        hp_input.setText("0");
        attack_input.setText("0");
        defense_input.setText("0");
        radio_group_id.clearCheck();
        spinner_id.setSelection(0);
        national_input.setTextColor(Color.BLACK);
        species_input.setTextColor(Color.BLACK);
        weight_input.setTextColor(Color.BLACK);
        name_input.setTextColor(Color.BLACK);
        height_input.setTextColor(Color.BLACK);
        hp_input.setTextColor(Color.BLACK);
        attack_input.setTextColor(Color.BLACK);
        defense_input.setTextColor(Color.BLACK);
        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
    }

    Spinner spinner_id;
    EditText height_input;
    EditText name_input;
    EditText national_input;
    EditText species_input;
    EditText weight_input;
    EditText hp_input;
    EditText attack_input;
    EditText defense_input;
    RadioGroup radio_group_id;
    RadioButton female_id;
    RadioButton male_id;
    RadioButton unk_id;
    Button save_id;
    Button reset_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);

        height_input = findViewById(R.id.height_input);
        name_input = findViewById(R.id.name_input);
        national_input = findViewById(R.id.national_input);
        species_input = findViewById(R.id.species_input);
        weight_input = findViewById(R.id.weight_input);
        hp_input = findViewById(R.id.hp_input);
        attack_input = findViewById(R.id.attack_input);
        defense_input = findViewById(R.id.defense_input);
        radio_group_id = findViewById(R.id.radio_group_id);
        female_id = findViewById(R.id.female_id);
        male_id = findViewById(R.id.male_id);
        unk_id = findViewById(R.id.unk_id);
        save_id = findViewById(R.id.save_id);
        reset_id = findViewById(R.id.reset_id);

        reset_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetEvent(v);
            }
        });

        save_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputHeight = height_input.getText().toString();
                String inputName = name_input.getText().toString();
                String inputSpecies = species_input.getText().toString();
                String inputWeight = weight_input.getText().toString();
                int inputNat = Integer.parseInt(String.valueOf(national_input.getText()));
                int inputHP = Integer.parseInt(String.valueOf(hp_input.getText()));
                int inputDef = Integer.parseInt(String.valueOf(defense_input.getText()));
                int inputAtt = Integer.parseInt(String.valueOf(attack_input.getText()));

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

                //Validating Species
                if(isValidSpec(inputSpecies)) {
                    species_input.setTextColor(Color.BLACK);
                } else {
                    species_input.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Invalid Species", Toast.LENGTH_SHORT).show();
                }

                //Validating Weight
                if(isValidWeight(inputWeight)) {
                    weight_input.setTextColor(Color.BLACK);
                } else {
                    weight_input.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Invalid Weight", Toast.LENGTH_SHORT).show();
                }

                //Validating HP
                if(isValidHP(inputHP)) {
                    hp_input.setTextColor(Color.BLACK);
                } else {
                    hp_input.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Invalid HP", Toast.LENGTH_SHORT).show();
                }

                //Validating Attack
                if(isValidAtt(inputAtt)) {
                    attack_input.setTextColor(Color.BLACK);
                } else {
                    attack_input.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Invalid Attack", Toast.LENGTH_SHORT).show();
                }

                //Validating Defense
                if(isValidDef(inputDef)) {
                    defense_input.setTextColor(Color.BLACK);
                } else {
                    defense_input.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Invalid Defense", Toast.LENGTH_SHORT).show();
                }

                //Save event if all fields are valid
                if(isValidName(inputName) && isValidHeight(inputHeight) && isValidNat(inputNat) && isValidSpec(inputSpecies) && isValidHP(inputHP) && isValidAtt(inputAtt) && isValidDef(inputDef)) {
                    saveEvent();
                }
            }

            public boolean isValidNat(int inputNat) {
                return inputNat >= 0 && inputNat <= 1010;
            }

            public boolean isValidName(String inputName) {
                String pattern = "^[a-zA-Z. ]{3,12}$";
                return inputName.matches(pattern);
            }

            public boolean isValidHeight(String heightInput) {
                try {
                    double height = Double.parseDouble(heightInput);
                    boolean h = height >= 0.3 && height <= 19.99;
                    return String.format("%.2f", height).equals(heightInput);
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            public boolean isValidSpec(String inputSpecies) {
                String pattern = "^[a-zA-Zé ]*$";
                return inputSpecies.matches(pattern);
            }

            public boolean isValidWeight(String weightInput) {
                try {
                    double weight = Double.parseDouble(weightInput);
                    boolean w = weight >= 0.1 && weight <= 820.0;
                    return String.format("%.2f", weight).equals(weightInput);
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            public boolean isValidHP(int inputHP) {
                return inputHP >= 1 && inputHP <= 362;
            }

            public boolean isValidAtt(int inputAtt) {
                return inputAtt >= 5 && inputAtt <= 526;
            }

            public boolean isValidDef(int inputDef) {
                return inputDef >= 5 && inputDef <= 614;
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

        //Radio Group
        radio_group_id.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.female_id) {}
                else if (checkedId == R.id.male_id) {}
                else if (checkedId == R.id.unk_id) {}
            }
        });
    }
}
