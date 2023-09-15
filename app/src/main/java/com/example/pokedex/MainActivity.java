package com.example.pokedex;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


        public void saveEvent(View v) {

            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        }
        public void resetEvent(View v) {

        Toast.makeText(this, "Reset", Toast.LENGTH_LONG).show();
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        }

}